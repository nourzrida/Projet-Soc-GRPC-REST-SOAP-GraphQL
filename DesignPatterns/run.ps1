# Script de lancement pour le projet DesignPatterns
# Usage: Exécuter dans PowerShell depuis le répertoire du projet
# Lance l'application et affiche les 50 dernières lignes du log
Start-Process -FilePath java -ArgumentList '-jar', '.\\target\\DesignPatterns-1.0-SNAPSHOT.jar' -NoNewWindow
Start-Sleep -Seconds 1
Get-Content .\\game.log -Tail 50
