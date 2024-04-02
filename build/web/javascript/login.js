const playButton = document.getElementById('playButton');
const playIcon = document.getElementById('play-icon');
var play = false;
const audio = new Audio('musica/s1.mp3');
playButton.addEventListener('click', function() {
  if (!play){
      audio.play();
    playIcon.src = 'img/pause.png'; // Reemplaza 'nueva_imagen.png' con la ruta de tu nueva imagen
    play = true;
  }else{
    audio.pause();
    playIcon.src = 'img/play.png'; // Reemplaza 'nueva_imagen.png' con la ruta de tu nueva imagen
    play = false;
  }
});