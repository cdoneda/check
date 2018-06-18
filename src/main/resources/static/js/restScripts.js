function handleRestError(error) {
	if (error && error.status == 403) {
		window.location.href = new URI();
		return false;
	}
	return 'Erro: ' + JSON.stringify(result);
}

function asyncPost(url, formId, holderId, onSuccess) {
	$.ajax({
		type : "POST",
		url : url,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		cache : false,
		data : new FormData($(formId)[0]),
		success : function(response) {
			if (holderId)
				$(holderId).html(response);
			if (onSuccess)
				onSuccess(response)
		},
		error : function(error) {
			var result = handleRestError(error);
			if (result)
				alert(result);
		}
	});
}

function asyncGet(url, holderId, onSuccess) {
	$.ajax({
		type : "GET",
		url : url,
		processData : false,
		contentType : false,
		cache : false,
		success : function(response) {
			if (holderId)
				$(holderId).html(response);
			if (onSuccess)
				onSuccess(response)
		},
		error : function(error) {
			var result = handleRestError(error);
			if (result)
				alert(result);
		}
	});
}
