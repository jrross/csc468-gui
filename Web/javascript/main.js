//ROSS: MANAGE
function History() {
	var undoRedos = [];
	var index = 0;
	//adds a new UndoRedo to the history list
	this.add = function(action){
		undoRedos.length = index;
		undoRedos.push(action);
		index = undoRedos.length;
		
		action.execute();
		updateButtons();
	}
	//executes undo, performs unexecute on UndoRedo object at the index
	//and steps back with the index
	this.undo = function(){
		if(index > 0){
			var action = undoRedos[index-1];
			action.unexecute();
			index = index - 1;
			printNotes(-1);
			updateButtons();
		}
	}
	//executes redo, performs execute on UndoRedo object at the index
	//and steps forward with the index 
	this.redo = function(){
		if(index < undoRedos.length){
			var action = undoRedos[index];
			action.execute();
			index = index + 1;
			printNotes(-1);
			updateButtons()
		}
	}
	//indicates whether or not the index is at the start
	this.canUndo = function(){
		return index > 0;
	}
	//indicates whether or not the index is at the end
	this.canRedo = function(){
		return index < undoRedos.length;
	}
}
//ROSS: ACTION
function UndoRedo(undo,redo){
	this.unexecute = undo;
	this.execute = redo;
}
//goes through all the notes currently stored, and prints them
//to their specified location on the bars (noteSheet) parameter tells
//which one of the notes to highlight in yellow, if not highlighting
//any, send in -1
function printNotes(target) {
	var totWidth = 0;
	//calculate the total width needed
	for(var i = 0; i < noteList.length; i++){
		var currWidth = 10 + (noteList[i].length * 40);
		totWidth += 10 +currWidth;
	}
	if(totWidth < 500){
		totWidth = 500;
	}
	//set box width to calculated width
	document.getElementById("noteSheet").width = totWidth;
	
	var canvas = document.getElementById("noteSheet");
	var ctx = canvas.getContext("2d");
	ctx.clearRect(0, 0, totWidth, 120);
	ctx.fillStyle = "#333333";
	//draw lines
	ctx.fillRect(0, 0, totWidth, 2.5);
	ctx.fillRect(0, 25, totWidth, 2.5);
	ctx.fillRect(0, 50, totWidth, 2.5);
	ctx.fillRect(0, 75, totWidth, 2.5);
	ctx.fillRect(0, 100, totWidth, 2.5);
	
	var pos = 0;
	for(var i = 0; i < noteList.length; i++){
		//if the target make yellow, otherwise black
		if(i == target){
			ctx.fillStyle = "#FFFF00";
		}
		else {
			ctx.fillStyle = "#000000";
		}
		var yval = getNoteHeight(noteList[i].pitch);
		//make notes with longer lengths longer
		var width = 10 + (noteList[i].length * 40);
		ctx.fillRect(pos, yval, width, 20);
		pos += 10 + width;
	}
	
}
//gets the height value on the bars for a given note
function getNoteHeight(note){
	if(note == "C")
		return 91.25;
	else if(note == "D")
		return 78.75;
	else if(note == "E")
		return 66.25;
	else if(note == "F")
		return 53.75;
	else if(note == "G")
		return 41.25;
	else if(note == "A")
		return 28.75;
	else if(note == "B")
		return 16.25;
	else if(note == "C'")
		return 3.75;
}
//Handles when the pitch is changed. Finds which button was pressed and
//assign the corresponding pitch, send the action to an UndoRedo object
//and add to the history
function pitchEvent(event) {
	var prevPitch = document.getElementById("currPitch").innerHTML;
	var currPitch;
	if( event.target.id == "buttonC" )
		 currPitch = "C";
	else if( event.target.id == "buttonD" )
		currPitch = "D";
	else if( event.target.id == "buttonE" )
		currPitch = "E";
	else if( event.target.id == "buttonF" )
		currPitch = "F";
	else if( event.target.id == "buttonG" )
		currPitch = "G";
	else if( event.target.id == "buttonA" )
		currPitch = "A";
	else if( event.target.id == "buttonB" )
		currPitch = "B";
	else if( event.target.id == "buttonC2" )
		currPitch = "C'";
	//ROSS: ACTION
    undo = function(){document.getElementById("currPitch").innerHTML = prevPitch;}
	redo = function(){document.getElementById("currPitch").innerHTML = currPitch;}
	hist.add(new UndoRedo(undo,redo));
 }
 //Handles when the length is changed, changes the current indicated length
 //and puts the information into an UndoRedo, and adds it to the history
function lengthEvent(){
	var currVal = document.getElementById("setText").value;
	var prevVal = document.getElementById("currLen").innerHTML;
	//ROSS: ACTION
	undo = function(){document.getElementById("currLen").innerHTML = prevVal;}
	redo = function(){document.getElementById("currLen").innerHTML = currVal;}
	hist.add(new UndoRedo(undo,redo));
	updateButtons();
}
//Handles when a note is confirmed, adds the new note to the list of notes,
//and adds the information to the UndoRedo, adds to history
function confirmEvent(){
	var newNote = {pitch: document.getElementById("currPitch").innerHTML, 
				  length: document.getElementById("currLen").innerHTML};
	//ROSS: ACTION
	undo = function(){noteList.pop();}
	redo = function(){noteList.push(newNote);}
	hist.add(new UndoRedo(undo, redo));
	printNotes(-1);
	updateButtons();
}
//Handles play event, calls recursive function to play song
function playEvent(){
	playNotes(0);
}
//Handles save event, takes all of the notes in the song and puts it into a 
//string and passes off to php where it is handled
function saveEvent(){
	var song = "";
	var file = prompt("please enter a file name (no extension):");
	//if file name note valid
	if(file == null || file == ""){
		return;
	}
	file += ".txt";
	for(var i = 0; i < noteList.length; i++){
		song += noteList[i].pitch + "-" + noteList[i].length;
		if(i != noteList.length-1){
		}
			song += "-";
	}
	window.location.href = "main.php?save=" + song + "&file=" + file;
}
 

//disables/enables undo and redo as necessary
function updateButtons(){
  	document.getElementById("undo").disabled = !hist.canUndo();	   
	document.getElementById("redo").disabled = !hist.canRedo();
}
//recursive function to play notes
function playNotes(index){
	if(index >= noteList.length || noteList[index].pitch == ""){
		printNotes(-1);
		return;
	}
	var sound = getNoteAudio(noteList[index]);
    sound.play();
	sound.currentTime = 5 - noteList[index].length;
	printNotes(index);
	sound.onended = function(){playNotes(index + 1)};
}
//gets the corresponding audio object for a specific note
function getNoteAudio(note){
	switch(note.pitch){
		case "C":
			return audioList[0];
		case "D":
			return audioList[1];
		case "E":
			return audioList[2];
		case "F":
			return audioList[3];
		case "G":
			return audioList[4];
		case "A":
			return audioList[5];
		case "B":
			return audioList[6];
		case "C'":
			return audioList[7];
	}
}
//Loads a song from the invisible currNotes field in php/html, puts into
//song list and then calls function to print them
function loadSong(){
	var song = document.getElementById("currNotes").innerHTML;
	document.getElementById("currNotes").innerHTML = "";
	var items = song.split("<br>");
	for(var i = 0; i < items.length; i++){
		if(items[i] != "" && items[i] != " "){
			var currNote = items[i].split(" ");
			var newNote = {pitch: currNote[0], 
						  length: currNote[1]};
			noteList.push(newNote);
		}
	}
	printNotes(-1);
}

var hist = new History();
var noteList = [];
//This preloads all the audio files so they don't lag when played
var audioList = [new Audio("res/C4_261.63Hz_5s.wav"),
				 new Audio("res/D4_293.66Hz_5s.wav"),
				 new Audio("res/E4_329.63Hz_5s.wav"),
				 new Audio("res/F4_349.23Hz_5s.wav"),
				 new Audio("res/G4_392Hz_5s.wav"),
				 new Audio("res/A4_440Hz_5s.wav"),
				 new Audio("res/B4_493.88Hz_5s.wav"),
				 new Audio("res/C5_523.25Hz_5s.wav")];

//attach all functions to html elements
window.onload = function() {
	document.getElementById("buttonC").onclick = pitchEvent;
	document.getElementById("buttonD").onclick = pitchEvent;
	document.getElementById("buttonE").onclick = pitchEvent;
	document.getElementById("buttonF").onclick = pitchEvent;
	document.getElementById("buttonG").onclick = pitchEvent;
	document.getElementById("buttonA").onclick = pitchEvent;
	document.getElementById("buttonB").onclick = pitchEvent;
	document.getElementById("buttonC2").onclick = pitchEvent;
	document.getElementById("set").onclick = lengthEvent;
	document.getElementById("confirm").onclick = confirmEvent;
	document.getElementById("undo").onclick = hist.undo;
	document.getElementById("redo").onclick = hist.redo;
	document.getElementById("play").onclick = playEvent;
	document.getElementById("save").onclick = saveEvent;
	
	loadSong();
	
	updateButtons();
}
