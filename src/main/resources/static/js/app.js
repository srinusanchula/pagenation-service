$(document).ready(function() {
    changePageAndSize();
});

function changePageAndSize() {
    $('#pageSizeSelect').change(function(evt) {
        window.location.replace("/devices?page=1&size=" + this.value + "&sortBy=" + $("#sortBySelect").val());
    });

    $('#sortBySelect').change(function(evt) {
        window.location.replace("/devices?page=1&size=" + $("#pageSizeSelect").val() + "&sortBy=" + this.value);
    });
}
