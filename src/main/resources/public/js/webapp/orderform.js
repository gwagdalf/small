var OrderForm = function () {

	var Init = function () {
	$("#btnAcceptOrder").click(Events.OnBtnOrderClick)

	};

	var Control = {

	};

	var Events ={
	    OnBtnOrderClick: function(){
	        // UI 가 너무 많다 ㅎㅎㅎ
	        // 일단 구색만 ㅎㅎ

	        //Sync before form submit
	        $("#txtOrderQty").val($("#txtOrderQuantity").val());

	        var sForm = $("#orderForm");
	        sForm.attr("action", "/order/placeOrder").submit();


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

	var Data = {
		TotalCount: 0,
	}

	Init();

	OrderForm.Control = Control;
	OrderForm.Event = Event;

};

$(function () {
	new OrderForm();
});
