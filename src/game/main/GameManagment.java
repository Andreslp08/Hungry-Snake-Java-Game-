package game.main;

import game.characters.Element;
import game.controller.PauseMenuInputHandler;
import game.controller.SnakeInputHandler;
import game.databases.GameDB;
import game.ui.Difficulty;
import game.ui.components.GameButton;
import game.ui.GameCanvas;
import game.ui.GameOver;
import game.ui.SettingsMenu;
import game.ui.GameWindow;
import game.ui.Hud;
import game.ui.LoadGame;
import game.ui.Menu;
import game.ui.PauseMenu;
import game.ui.ScreenManagment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class GameManagment {

    public static LoadGame loadGame;
    public static GameWindow gameWindow;
    public static Menu menu;
    public static SettingsMenu settingsMenu;
    public static Hud hud;
    public static PauseMenu pauseMenu;
    public static GameOver gameOver;
    public static Difficulty difficulty;
    public static GameLevel.DifficultyMode difficultyMode;
    public static GameCanvas gameCanvas;
    public static Game game;
    public static Timer timerLoadGame;
    private static final int LOAD_TIME = 0;
    public static String gameTitle = "Hungry Snake";
    public static GameSound test;
    public static SnakeInputHandler snakeInputHandler;
    public static PauseMenuInputHandler pauseMenuInputHandler;
    public GameDB gameDB;
    public static String USER_NICK = "Player01";

    public GameManagment() {
        test = new GameSound("/game/assets/sounds/test.wav", SoundManagment.SoundType.UI);
        test.setDefaultVolume(0);
        test.loop(true);
        test.start();
        // load GAME DATABASE SETTINGS
        gameDB = new GameDB();
        gameDB.connect();
        ScreenManagment.WIDTH = gameDB.getScreenWidth();
        ScreenManagment.HEIGHT = gameDB.getScreenHeight();
        ScreenManagment.FPS_REQUIRED = gameDB.getFPS();
        SoundManagment.GAME_VOLUME = gameDB.getGameVolume();
        SoundManagment.UI_VOLUME = gameDB.getUIVolume();
        loadGame = new LoadGame();
        gameWindow = new GameWindow();
        menu = new Menu();
        settingsMenu = new SettingsMenu();
        pauseMenu = new PauseMenu();
        gameOver = new GameOver();
        hud = new Hud();
        gameCanvas = new GameCanvas();
        difficulty = new Difficulty();
        // order layers
        gameWindow.getLayers().add(gameCanvas, Integer.valueOf(1));
        gameCanvas.setVisible(false);
        gameWindow.getLayers().add(hud, Integer.valueOf(2));
        hud.setVisible(true);
        gameWindow.getLayers().add(pauseMenu, Integer.valueOf(3));
        pauseMenu.setVisible(false);
        gameWindow.getLayers().add(gameOver, Integer.valueOf(4));
        gameOver.setVisible(false);
        gameWindow.getLayers().add(difficulty, Integer.valueOf(5));
        difficulty.setVisible(false);
        gameWindow.getLayers().add(settingsMenu, Integer.valueOf(6));
        settingsMenu.setVisible(false);
        gameWindow.getLayers().add(menu, Integer.valueOf(7));

        // handlers and controllers
        startGameHandler();
        continueGame();
        retryHandler();
        returnMenuHandler();
        exitGameHandler();
        closeWindowHandler();
        inputHandlers();
        selectDifficulty();
        settingsHandler();

        // set game volume
        SoundManagment.updateVolume();
    }

    public void updateInputHandler() {
        snakeInputHandler.SNAKE_UP = gameDB.getKey(GameDB.UP_CONTROL_ATTR);
        snakeInputHandler.SNAKE_DOWN = gameDB.getKey(GameDB.DOWN_CONTROL_ATTR);
        snakeInputHandler.SNAKE_LEFT = gameDB.getKey(GameDB.LEFT_CONTROL_ATTR);
        snakeInputHandler.SNAKE_RIGHT = gameDB.getKey(GameDB.RIGHT_CONTROL_ATTR);
        pauseMenuInputHandler.PAUSE_KEY = gameDB.getKey(GameDB.PAUSE_CONTROL_ATTR);
    }

    public void inputHandlers() {
        snakeInputHandler = new SnakeInputHandler(this);
        gameWindow.addKeyListener(snakeInputHandler);
        gameCanvas.addKeyListener(snakeInputHandler);
        pauseMenuInputHandler = new PauseMenuInputHandler(this);
        gameWindow.addKeyListener(pauseMenuInputHandler);
        gameCanvas.addKeyListener(pauseMenuInputHandler);
        updateInputHandler();
    }

    public void startGameHandler() {
        menu.getStartButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                menu.setVisible(false);
                difficulty.setVisible(true);
            }
        });
    }

    public void selectDifficulty() {
        difficulty.getEasyButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                difficulty.setVisible(false);
                // load game
                if (game != null) {
                    game.stop();
                }
                Element.elementList.clear();
                difficultyMode = GameLevel.DifficultyMode.EASY;
                hud.reset();
                game = new Game(gameCanvas, difficultyMode);
                game.init();
//                hud.setVisible(true);
                gameCanvas.setVisible(true);
            }

        });
        difficulty.getNormalButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                difficulty.setVisible(false);
                // load game
                if (game != null) {
                    game.stop();
                }
                Element.elementList.clear();
                difficultyMode = GameLevel.DifficultyMode.NORMAL;
                hud.reset();
                game = new Game(gameCanvas, difficultyMode);
                game.init();
//                hud.setVisible(true);
                gameCanvas.setVisible(true);
            }

        });
        difficulty.getHardButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                difficulty.setVisible(false);
                // load game
                if (game != null) {
                    game.stop();
                }
                Element.elementList.clear();
                difficultyMode = GameLevel.DifficultyMode.HARD;
                hud.reset();
                game = new Game(gameCanvas, difficultyMode);
                game.init();
//                hud.setVisible(true);
                gameCanvas.setVisible(true);
            }

        });

    }

    public void settingsHandler() {
        menu.getConfigButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                menu.setVisible(false);
                settingsMenu.setVisible(true);
            }
        });
    }

    public void continueGame() {
        pauseMenu.getContinueButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                game.setIsPaused(false);
                pauseMenu.setVisible(false);
            }

        });

    }

    public void exitGameHandler() {
        menu.getExitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.exit(0);
            }

        });
        pauseMenu.getExitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.exit(0);
            }

        });

        gameOver.getExitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.exit(0);
            }

        });
    }

    public void returnMenuHandler() {
        pauseMenu.getMenuButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                pauseMenu.setVisible(false);
                menu.setVisible(true);
                game.stop();
                Element.elementList.clear();
            }

        });
        gameOver.getMenuButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                gameOver.setVisible(false);
                menu.setVisible(true);
                game.stop();
                Element.elementList.clear();
            }

        });
    }

    public void closeWindowHandler() {
        gameWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void pauseGame() {
        if (menu.isVisible() == false && gameOver.isVisible() == false) {
            if (game.isIsPaused() == false) {
                game.setIsPaused(true);
                pauseMenu.setVisible(true);
            } else {
                game.setIsPaused(false);
                pauseMenu.setVisible(false);
            }
        }

    }

    public void retryHandler() {
        gameOver.getRetryButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                gameOver.setVisible(false);
                Element.elementList.clear();
                hud.reset();
                game.stop();
                game = new Game(gameCanvas, difficultyMode);
                game.init();
            }

        });
    }

    public void init() {
        timerLoadGame = new Timer(LOAD_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadGame.load() == true) {
                    loadGame.getFrame().dispose();
                    gameWindow.setVisible(true);
                    timerLoadGame.stop();
                }
            }
        });
        timerLoadGame.start();
    }
}
