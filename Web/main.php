<!––
The following project can be reached at:
https://dev.mcs.sdsmt.edu/~7275982/GUI/main.php
for more information on its workings, please visit
the help page.
>
<?php
// Start the session
session_start();

// Check if save button was hit, if so, save to designated file
if(isset($_GET["save"])){
	$filename = "saves/";
	$filename .= $_GET["file"]; 
	$file = fopen($filename, "w");
	fwrite($file, $_GET["save"]);
}

?>

<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title> MP3 Maker </title>
  <script type="text/javascript" 
      src="javascript/main.js"></script>
  <link href="lib/style.css" rel="stylesheet" type="text/css">
</head>
<body>
  <ul>
    <li><a href="main.php">Main page</a></li>
    <li><a href="files.php">Files</a></li>
    <li><a href="help.html">Help</a></li>
  </ul>
  <p><button id="buttonC">C</button><button id="buttonD">D</button><button id="buttonE">E</button><button id="buttonF">F</button></button><button id="buttonG">G</button><button id="buttonA">A</button><button id="buttonB">B</button><button id="buttonC2">C</button></p>
  <p>Note Length: <input id="setText" type="text" name="setText"><button id="set">Set</button></p>
  <p><button id="confirm">Confirm Note</button><button id="undo">Undo</button><button id="redo">Redo</button>
  <p><button id="save">Save</button><button id="play">Play</button></p>
  <p>Current note: <span id="currPitch"></span></p>
  <p>Current length: <span id="currLen"></span></p>
  <canvas id="noteSheet" width="500" height="120"></canvas>
<?php
// If page was reached indicated with a file, load in the song from the file
if(isset($_GET["file"])){
  $song = file_get_contents('saves/'.$_GET["file"]);
  $song_arr = explode("-", $song);
  $length = count($song_arr);
  echo "<p hidden><span id=\"currNotes\">";
  for($i=0; $i < $length; $i+=2){
    echo $song_arr[$i]." ".$song_arr[$i+1]."<br>";
  }
  echo "</span></p>";
}
else{
  echo "<p hidden><span id=\"currNotes\"></span></p>";
}
?>
<body>
</html>
