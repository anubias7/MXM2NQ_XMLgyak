<!DOCTYPE html>
<html lang="hu">

<head>
	<title>Kérdőív</title>
	<meta charset="UTF-8">
</head>

<body>
	<font style="font-size: 16pt;">
		<h2>HTML5</h2>

		<?php
		echo ("<strong>Név:</strong>" . $_POST['nev'] . "<br><br>");

		echo ("<strong>PIN kód:</strong>" . $_POST['kod'] . "<br><br>");

		echo ("<strong>Gyümölcs:</strong>" . $_POST['gyumolcs'] . "<br><br>");

		echo ("<strong>Életkor:</strong>" . $_POST['eletkor'] . "<br><br>");

		echo ("<strong>Lábméret:</strong>" . $_POST['labmeret'] . "<br><br>");

		echo ("<strong>Önbizalom:</strong>" . $_POST['onbizalom'] . "<br><br>");

		?>
		<a href="urlap.html">Vissza a kérdőívre...</a>
	</font>
</body>

</html>