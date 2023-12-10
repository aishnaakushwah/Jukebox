create database jukebox;
use jukebox;

create table songs(songId int primary key auto_increment, songName varchar(100), genre varchar(100), artist varchar(100), duration double, filepath varchar(90));
insert into songs(songName, genre, artist, duration, filepath) values('Iris', 'Rock', 'Goo Goo Dolls', 3.35, 'src/main/resources/Iris.wav');
insert into songs(songName, genre, artist, duration, filepath) values('Unravel', 'J-Pop', 'Toru Kitajima', 4.02, 'src/main/resources/Unravel.wav');
insert into songs(songName, genre, artist, duration, filepath) values('Alag Aasmaan', 'Indian Indie', 'Anuv Jain', 3.44, 'src/main/resources/Alag Aasmaan.wav');
insert into songs(songName, genre, artist, duration, filepath) values('Mr. Saxobeat', 'Pop', 'Alexandra Stan', 3.13, 'src/main/resources/Mr. Saxobeat.wav');
insert into songs(songName, genre, artist, duration, filepath) values('Blue Bird', 'J-Pop', 'Ikimono-gakari', 3.36, 'src/main/resources/Blue Bird.wav');
insert into songs(songName, genre, artist, duration, filepath) values('The Night We Met', 'Thai Indie', 'Lord Huron', 3.28, 'src/main/resources/The Night We Met.wav');
insert into songs(songName, genre, artist, duration, filepath) values('Socha Na Tha', 'Indian Indie', 'Zaeden', 2.40, 'src/main/resources/Socha Na Tha.wav');
insert into songs(songName, genre, artist, duration, filepath) values('Lets Fall in Love for the Night', 'Pop', 'FINNEAS', 3.09, 'src/main/resources/Lets Fall in Love for the Night.wav');

create table playlist(playlistId int primary key auto_increment, playlistName varchar(100));

create table songsInPlaylist(playlistId int, songId int);