<?php
//download the specified file
if(isset($_GET["download"])){
	$file="saves/".$_GET["download"];
	header('Content-Disposition: attachment; filename="'.$file.'"');
    readfile($file);
	die();
}
//delete the specified file
if(isset($_GET["delete"])){
	$file="saves/".$_GET["delete"];
	unlink($file);
	
}
//display error messages
if(isset($_GET["message"]) && $_GET["message"] != ""){
  echo "<p>".$_GET["message"]."</p>";
}
?>

<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title> MP3 Maker </title>
  <script type="text/javascript" 
      src="javascript/files.js"></script>
  <link href="lib/style.css" rel="stylesheet" type="text/css">
</head>
<body>
  <ul>
    <li><a href="main.php">Main page</a></li>
    <li><a href="files.php">Files</a></li>
    <li><a href="help.html">Help</a></li>
  </ul>
  <form action="upload.php" method = "post" enctype="multipart/form-data">
    <input id="fileUpload" type="file" name="fileUpload">
    <input id="upload" type="submit" value="Upload" name="submit">
  </form>
<?php
//go through all files, put them in html with load, download, and delete button
$dir = new DirectoryIterator("saves");
echo "<table>";
foreach ($dir as $fileinfo) {
    if (!$fileinfo->isDot()) {
        echo "<tr><td>", $fileinfo->getFilename(), "</td>";
		echo "<td><button id=\"load-", $fileinfo->getFilename(), "\" name=\"load\">Load</button></td>";
		echo "<td><button id=\"download-", $fileinfo->getFilename(), "\" name=\"download\">Download</button></td>";
		echo "<td><button id=\"delete-",  $fileinfo->getFilename(), "\" name=\"delete\">X</button></td>";
    }
}
echo "</table>";
?>

<body>
</html>
