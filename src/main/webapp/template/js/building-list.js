function assigmentBuilding() {
	openAssigmentBuilding();
}
function openAssigmentBuilding() {
	$('#buildingModal').modal();
}

$('#searchBuilding').click(function(e) {
	e.preventDefault();
	$('#mode').val('SEARCH');
	$('#buildingForm').submit();
});


function showStaffAssignment(data) {
	$.ajax({
		type: "POST",
		url: "${UserApi}?type=SHOW_STAFF_ASSIGNMENT",
		dataType: "json",
		success: function(response) {
			var html ='	';
			html+= '<tr>'
		},
		error: function(response) {
			console.log('sussces');
		}
	});
}