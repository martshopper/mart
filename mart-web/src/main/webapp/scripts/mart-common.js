mart = {
	loadCombo: function(options) {
		var restURL = options['restURL'];
        var componentId = options['comboComponentId'];
        var findCmbRoot = options['comboRootRef'];
        var findCmbId = options['comboIdRef'];
        var findCmbValue = options['comboValueRef'];
        var sourceScreen = options['sourceScreen'];
        var setDefault = options['setDefault'];
        var defid = options['defaultid'];
		$("#"+componentId).empty();
		$("#"+componentId).append("<option value=''>--Select--</option>");
		$.ajax({
			url: restURL,
			type: 'GET',
			success: function (jsonObject) {
				 if (jsonObject != null && JSON.stringify(jsonObject) != "" && JSON.stringify(jsonObject) != null && jsonObject[findCmbRoot] != null) {
					var dataArray = jsonObject[findCmbRoot];
					for (var i = 0; i < dataArray.length; i++) {
                        var id = dataArray[i][findCmbId];
                        var name = dataArray[i][findCmbValue];
						$("#" + componentId).append("<option value=" + id + ">"+ name + "</option>");
					}
				}
			},
			error: function (error) {
				globalErrorAlert($.parseJSON(error.responseText).exception);
			},            
		});
	}
};
martSession = {
	userId: "",
	username: "",
	userFullName: ""
};