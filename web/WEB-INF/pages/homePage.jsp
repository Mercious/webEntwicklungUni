
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:pageWithFooterHeader pageTitle="Home">
	<section>
		<div class="container white">
			<h1> Herzlich Willkommen</h1>
				<p> PC selbst am PC Konfigurator zusammenstellen - egal ob Gaming PC oder Office Rechner! Unser PC Konfigurator erlaubt es, einen ganz pers&ouml;nlichen Computer von Grund auf selbst zu konfigurieren. Ob Gaming-, Office- oder Silent-PC - konfiguriere ihn Dir selber, ganz ohne Einschr&auml;nkungen! Egal f&uuml;r welchen Verwendungszweck Du einen neuen PC oder eine neue Workstation kaufst: mit dem extrem umfangreichen PC Konfigurator im MIFCOM PC Onlineshop wird der Wunsch-Computer ganz schnell Realit&auml;t.
					Hier kannst Du nach Lust und Laune drauflos konfigurieren &ndash; erstelle Deinen neuen PC von Grund auf selbst! &Uuml;bertaktung, besonders leise Komponenten, auf das Minimum reduziert, als Multimedia Center geeignet, f&uuml;r Extreme Gamer oder einfach als Familien PC f&uuml;r zu Hause: Unser PC Konfigurator kann f&uuml;r jedes Einsatzgebiet angewendet werden.
				</p>
				
				<p>
					Mit dem PC-Store PC-Konfigurator zum Traum-PC &ndash; auch f&uuml;r Laien Der PC-Store Konfigurator f&uuml;r die pers&ouml;nliche Zusammenstellung eines Computers ist f&uuml;r Profis, aber noch viel wichtiger, auch f&uuml;r absoluten Laien geeignet. In f&uuml;nf Schritten gelangst Du von der Auswahl der Hardware zur &Uuml;bersicht des fertigen PCs.
					Du brauchst dir &uuml;ber Kompatibilit&auml;t keine Gedanken machen. Das haben wir bereits f&uuml;r dich &uuml;bernommen. Jede zur Verf&uuml;gung stehende Hardware wurde von uns sorgf&auml;ltig bestimmt und ist mit den von dir ausgew&auml;hlten Teilen zu 100% kompatibel. Der PC-Konfigurator pr&uuml;ft nach jeder &Auml;nderung die Kompatibilit&auml;t der ausgew&auml;hlten Komponenten und gibt daraufhin einen Warnhinweis bei Inkompatibilit&auml;tsproblemen aus. Sollte aufgrund einer Auswahl ein schon gew&auml;hltes Bauteil nicht in das System passen, weist der MIFCOM PC-Konfigurator Dich darauf hin und liefert Verbesserungsvorschl&auml;ge.
					Aufeinander abgestimmte Wunsch-Hardware im PC Konfigurator
				</p>
		</div>
	</section>
	<section>
		<div class="container headline">Unsere Empfehlungen</div>
	<div class="container">
	<c:forEach items="${articleTeaserList}" var="article">
		<custom:articleTeaser articleName="${article.articleName}" articleID="${article.articleID}" articlePrice="${article.price}"/>
	</c:forEach>
	</div>
	</section>
</custom:pageWithFooterHeader>