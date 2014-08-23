## Beschreibung der Initialisierung des Halbjahres
### Schulhalbjahre
Die Halbjahre werden immer zum 1.3. und 1.9. eines Jahres angelegt.
Der Algorithmus ist wie folgt:
- Am 1.3. Aktuelles Jahr und Beide-Halbjahre. Das Datum für den Nachteilsausgleich wird vom letzten SHJ kopiert.
- Am 1.9. (Aktuelles Jahr + 1) und 1.Halbjahr. Die Datümer bleiben leer.
Die Schuljahre sind nicht selektierbar.

### Zeugnisformulare
Die Vorbelegung erfolgt für jeder Klasse wie folgt,

- beschreibung:  2004-05/Hj-?/Kl-? oder Schulhalbjahr.createRelativePathName + "/Kl-" Klasse.calculateKlassenname().
- templateFileName:  LKS
- leitspruch 1/2 und quelle 1/2: im 2.HJ Übernahme vom 1. Hj. (also LK) im 1Hj leer.
- ausgabeDatum:  leer
- nachteilsAusgleichsDatum: leer
- schulfachDetailInfos: LKS
- klasse und schulhalbjahr: ergibt sich aus der Schleife

Zur den Algorithmen LK und LKS:

- LK (Letztes zur gleichen Klasse): Suche das mit der höchsten Id zur gleichen KLasse oder zur Klasse, das mit
dem vorherigem Schulhalbjahr
- LKS (Letztes zur gleichen Klassenstufe): Ermittle die gleiche Klassenstufe und suche dazu das letzte.
Ist im 2. Hj. identisch mit LK. Im anderen Fall muss man erst bei der Klasse das Jahr des Jahrgangs runterzählen (suffix ist egal).

### Zu beachten:
Die x-Klassen müssen wieder aufgelöst werden.
