mart = {
	loadCombo: function(options) {
		var elementId = options['elementId'];
		var url = options['url'];
		var root = options['root'];//Need To Use
		var value = options['value'];//Need To Use
		var text = options['text'];//Need To Use
		$("#"+elementId).empty();
		$("#"+elementId).append("<option value='-1'>--Select--</option>");
		$.ajax({
			url: url,
			type: 'GET',
			success: function (data) {
				if(data != null && data != undefined && data.typeTOs != null) {
					for(var i=0;i<data.typeTOs.length;i++){
						$("#"+elementId).append("<option value='"+data.typeTOs[i].id+"'>"+data.typeTOs[i].typeCode+"</option>");
					}
				}
			},
			error: function (error) {
				globalErrorAlert($.parseJSON(error.responseText).exception);
			},            
		});
	}
};