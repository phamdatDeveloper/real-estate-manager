function assigmentBuilding() {
	openAssigmentBuilding();
}
function openAssigmentBuilding() {
	$('#buildingModal').modal();
}

$('#addBuilding').click(function() {
	var data = {};
	console.log('run--------------');
	var buildingTypes = [];
	var formData = $('#buildingEdit').serializeArray();
	// data['name'] = 'abc';
	// data['rentArea'] = '500,600,700';
	// buildingTypes.push('NGUYEN_CAN');
	// buildingTypes.push('TANG_TRET');
	$.each(formData, function(index, v) {
		if (v.name == 'buildingTypes') {
			buildingTypes.push(v.value);
		} else {
			data["" + v.name + ""] = v.value;
		}
	});
	data['buildingTypes'] = buildingTypes;

	$.ajax({
		type: 'POST',
		url: 'http://localhost:8080/api-building',
		data: JSON.stringify(data),
		dataType: 'json',
		contentType: 'application/json',
		success: function(response) {
			console.log('success');

		},
		error: function(response) {
			console.log('failed');
			console.log(response);
		}

	});

});