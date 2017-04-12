function doDelete(id) {
	$.post("feature", {
            id: id,
            do: "delete"
    }).done(function(responseText) {
        location.reload();
    });
}

function feature(btn, id) {
	$.post("feature", {
            id: id,
            do: "feature"
    }).done(function(responseText) {
        btn.addClass("green");
        btn.text = "Featured!";
    });
}