var ListingPage = function () {

	var Init = function () {
	    Control.GetData();
	};

	var Control = {
		GetData: function () {

		    var param = {
                size: Data.PageSize,
                page: Data.CurrentPage,
                sort: "price,asc"
		    };

			function onSuccess(data) {

			    Data.TotalPage = data.totalPages - 1 ;
			    var length = data.content.length;
			    Data.TotalCount = data.totalElements;
			    Data.CurrentPage = data.number;
                var html = "";
                for(var i=0; i< length; i++)
                {
                    var item = data.content[i];
                    var isActive = (item.isActive == true && item.quantity > 0) ? "<strong>재고 있음</strong>":"<strong style='color:Red;'>재고 없음</strong>";
                   html += '<tr><td class="col-sm-9 col-md-7"><div class="media"><a class=" pull-left" href="#">' +
                    '<img class="media-object" src="/images/product-icon.png" style="width: 72px; height: 72px;"> </a>' +
                    '<div class="media-body"><h4 class="media-heading"><a href="/items/viewitem/' + item.id + '">' + item.name + '</a></h4>' +
                    '<span>상태: </span><span class="text-success">' + isActive + '</span>' +
                    '</div></div></td><td class="col-sm-1 col-md-1 text-center"><strong>'+ item.currency + ' ' + item.price + '</strong></td>' +
                    '</tr>';
                }

                $("#divListingPage").html(html)

                Pager.Init(Data.TotalCount);

			};

			function beforeSend() {
			}

			function onError() {
			};

			Utils.Request({ url: "/item-webservices/getItemList", method: "GET", cache: false, async: true }, param, onSuccess, onError, null, beforeSend);

		}
	};

	var Utils = {
		Request: function (requestInfo, data,
										onSuccess, // function(data, textStatus) { }
										onError, // function(XMLHttpRequest, textStatus, errorThrown) { }
										onComplete, // function(XMLHttpRequest, textStatus) { }
										beforeSend
										) {
			jQuery.ajax({
				url: requestInfo.url,
				data: data,
				type: requestInfo.method,
				success: onSuccess,
				error: onError,
				beforeSend: beforeSend,
				complete: onComplete,
				dataType: 'json',
				cache: requestInfo.cache,
				async: requestInfo.async
			});
		}
	};

	var Pager = {
		Init: function (tCount) {
			var startIdx = 1; //보여주는 첫 페이지
			var endIdx = 0; //보여주는 마지막 페이지
			var pageCnt = 5; //보여주는 페이지 갯수
			var pageIdx = 0; //현재 페이지 index
			var totalCnt = 0; //전체 row 갯수
			var pageEnd = 0; //마지막 페이지

			Data.TotalCount = tCount;

			pageSize = Data.PageSize;
			pageIdx = Data.CurrentPage;
			totalCnt = tCount;
			pageEnd = Math.floor(totalCnt / pageSize);

			if (totalCnt % pageSize > 0) pageEnd++;

			startIdx = Pager.SetStartPageIdx(pageIdx, pageCnt);
			endIdx = Pager.SetEndPageIdx(pageIdx, pageCnt, pageEnd);

			var html = '';
			if (startIdx > pageCnt) {
				html += '<li><a href="javascript:ListingPage.Pager.PageClick(' + (startIdx - 1) + ');">이전</a></li>';
			} else {
				//html += '<li><a>이전</a></li>';
			}

			for (var i = startIdx; i < endIdx; i++) {
    			html += '<li><a href="javascript:ListingPage.Pager.PageClick(' + i + ');" >' + i + '</a></li>';
			}

			if (pageEnd > endIdx) {
				html += '<li><a>...</a></li> <li><a href="javascript:ListingPage.Pager.PageClick(' + pageEnd + ');">' + pageEnd + '</a></li>';
				html += '<li><a href="javascript:ListingPage.Pager.PageClick(' + endIdx + ');">다음</a></li>\n';
			} else {
				//html += '<li><a>다음</a></li>\n';
			}

			$('.pagination').html(html);

		},
		PageClick: function (page) {
			Data.CurrentPage = page - 1;
			Control.GetData();
		},
		SetStartPageIdx: function (idx, pageCnt) {
			if (idx <= pageCnt) {
				return 1;
			}
			return Math.floor((idx - 1) / pageCnt) * pageCnt + 1;
		},
		SetEndPageIdx: function (idx, pageCnt, pageEnd) {
			var nextIdx = Pager.SetStartPageIdx(idx, pageCnt) + pageCnt;

			if (nextIdx > pageEnd) {
				nextIdx = pageEnd + 1;
			}
			return nextIdx;
		}
	};

	var Data = {
	    TotalPage:0,
		TotalCount: 0,
		CurrentPage: 0,
		PageSize : 5
		};

	Init();

	ListingPage.Pager = Pager;
	ListingPage.Control = Control;
	ListingPage.Event = Event;

};

$(function () {
	new ListingPage();
});
