function validateMenuForm() {
  var x = document.forms["menuForm"]["date_created"].value;
  if (!(/^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/.test(x))) {
    alert("Wrong date format. Date must be in format yyyy-mm-dd");
    return false;
  }
}