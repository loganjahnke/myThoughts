function doDelete(id) {
	$.post("feature", {
            id: id,
            do: "delete"
    }).done(function(responseText) {
        location.reload();
    });
}

function feature(id) {
	$.post("feature", {
            id: id,
            do: "feature"
    }).done(function(responseText) {
    	document.getElementById("feature-" + id).outerHTML = "";
    });
}

function unfeature(id) {
	$.post("feature", {
            id: id,
            do: "unfeature"
    }).done(function(responseText) {
        document.getElementById("unfeature-" + id).outerHTML = "";
    });
}