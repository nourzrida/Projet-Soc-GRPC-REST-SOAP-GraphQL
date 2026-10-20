# Tower Defense - Design Patterns Demo (Java 17 + JavaFX)

Ce projet démonstratif implémente un mini jeu Tower Defense en Java 17 avec JavaFX et plusieurs design patterns : State, Decorator, Composite et Factory, ainsi qu'un simple système de logging (`util.GameLogger`) qui écrit dans `game.log`.

Prérequis (Windows / PowerShell):
- JDK 17 installé et `java`/`javac` accessibles dans le PATH (vérifier avec `java -version`).
- Maven installé (`mvn` accessible) pour faciliter le lancement (`mvn javafx:run`).
- (Optionnel) JavaFX SDK si vous souhaitez exécuter le jar avec `--module-path` plutôt que via Maven.

Commandes utiles (PowerShell) :

# Vérifications
java -version
mvn -v

# Lancer via Maven (recommandé si Maven & JDK 17 présents)
cd "C:\\Users\\MSI\\Desktop\\SmartHealthPlatform\\DesignPatterns"
mvn clean package
mvn -Djavafx.platform=win javafx:run

# Ou construire puis lancer le jar (nécessite module-path JavaFX si vous n'exécutez pas via Maven)
mvn clean package
# si vous avez un JavaFX SDK dans C:\javafx-sdk-17 :
java --module-path "C:\\javafx-sdk-17\\lib" --add-modules javafx.controls,javafx.fxml -jar .\target\DesignPatterns-1.0-SNAPSHOT.jar

Notes importantes :
- Le système local doit utiliser Java 17. Le runtime `java -version` détecté sur cette machine est Java 8 — installez JDK 17 pour exécuter.
- Si `mvn` n'est pas installé, installez Apache Maven et relancez.

Vérifications runtime à effectuer après démarrage :
- Vérifier que `game.log` est créé et contient des lignes de log du format : `[yyyy-MM-dd HH:mm:ss] [TYPE] Message`.
- Cliquer sur "Play" dans l'interface pour déclencher `PlayingState` (GameLogger enregistre le changement d'état) et démarrer la boucle.
- Observer l'apparition d'entrées lors de la création d'une vague (WAVE), application d'un décorateur (DECORATOR) et changements d'état d'ennemis (ENEMY_STATE).

Structure importante des packages :
- `controller` : `GameController`, `GameLoop`
- `model` : `Enemy`, sous-types dans `model.enemies`
- `patterns.state` : états du jeu et ennemis (GameState, EnemyState...)
- `patterns.decorator` : tours, décorateurs
- `patterns.composite` : organisation des vagues
- `patterns.factory` : `EnemyFactory`
- `util` : `GameLogger`

Si vous voulez, je peux :
- Ajouter des tests unitaires simples pour chaque pattern.
- Corriger automatiquement la configuration si vous souhaitez rendre le jar auto-exécutable.
- Préparer un Dockerfile pour exécuter sans modifier votre machine.

---
README généré automatiquement par l'assistant.
