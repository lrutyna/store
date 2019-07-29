
function deleteProduct(id){
	$.get(
			"/remove-product",
		    {productId : id},
		    function(data) {}
	);
}


function deleteAccount(id){
	$.get(
			"accounts-panel/remove-account",
		    {accountId : id},
		    function(data) {
		    	location.reload();
		    }
	);
}


function editAccount(id){
	$.get(
			"accounts-panel/remove-account",
		    {accountId : id},
		    function(data) {
		    	location.reload();
		    }
	);
}


function getProductDetails(id){
	$.get(
			'product/' + id,
		    {productId : id},
		    function(data) {
		    	$('#productsTableTbody').empty();
		    	for (var i = 0; i < data.length; i++) {
		            drawRow(data[i]);
		        }
		    }
	);
}


function drawRow(rowData) {
    var row = $("<tr />")
    $("#productsTable").append(row); 
    row.append($("<td>" + rowData.id + "</td>"));
    row.append($("<td>" + rowData.name + "</td>"));
    row.append($("<td>" + rowData.description + "</td>"));
    row.append($("<td>" + rowData.price + "</td>"));
    row.append($("<td>" + rowData.quantityInStock + "</td>"));
    
    var aEdit = $("<a>Edytuj</a>");
    aEdit.addClass("btn btn-secondary");
    aEdit.attr('href', "/admin-panel/edit-product/" + rowData.id);
    row.append($("<td>" + "</td>").append($(aEdit)));
    
    var aDelete = $("<a>Usuń</a>");
    aDelete.addClass("btn btn-danger");
    aDelete.attr('href', "/admin-panel/remove-product/" + rowData.id);
    row.append($("<td>" + "</td>").append($(aDelete)));
}

function changeCategory(selectedCategoryName){
	var value = selectedCategoryName;
	
	if(value != ""){
		$.get(
				'/admin-panel/products-by-category/' + value,
			    {name : value},
			    function(data) {
			    	$('#productsTableTbody').empty();
			    	for (var i = 0; i < data.length; i++) {
			            drawRow(data[i]);
			        }
			    }
		);
	}
}

