<?php
  //keep uploads separate
  $target_dir = "saves/";
  $target_file = $target_dir .
    basename($_FILES["fileUpload"]["name"]);
  //Check if file already exists, delete if so
  if(file_exists($target_file)){
    unlink($target_file);
  }

  //Check file size
  if($_FILES["fileUpload"]["size"] > 5000){
    $message = "File is too large";
  }

  $message = 'Error uploading file';
  switch( $_FILES['fileUpload']['error'] ) {
    case UPLOAD_ERR_OK:
      $message = false;;
      break;
    case UPLOAD_ERR_INI_SIZE:
    case UPLOAD_ERR_FORM_SIZE:
      $message .= ' - file is too large.';
      break;
    case UPLOAD_ERR_PARTIAL:
      $message .= ' - file upload was not completed.';
      break;
    case UPLOAD_ERR_NO_FILE:
      $message .= ' - zero-length file uploaded.';
      break;
    default:
      $message .= ' - internal error
            #'.$_FILES['fileUpload']['error'];
      break;
  }
  //if OK thus far
  if( !$message ) {
    //check if it is a file we can handle
    if( !is_uploaded_file($_FILES['fileUpload']['tmp_name']) ) {
      $message = 'Error uploading file - unknown error.';
    } else {
    // NOW move the file to $target_file location, and confirm
    if( !move_uploaded_file($_FILES["fileUpload"]["tmp_name"], $target_file) ) {
      $message = 'Error uploading file - could not save upload
      (this will probably be a permissions problem in '.$dest.')';
    }
  }
}
header('Location: files.php?message='.$message);
?>
