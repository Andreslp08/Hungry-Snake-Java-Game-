/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ui;

import game.databases.GameDB;

public class ScreenManagment {


    public static int WIDTH, DEFAULT_WIDTH = 600;
    public static int HEIGHT, DEFAULT_HEIGHT = 600;
    public static int HUD_HEIGHT = 70;
    public static int FPS_REQUIRED, DEFAULT_FPS_REQUERIED = 60;

    public static final String[] SCREEN_OPTIONS_STR = {"600x600", "800x800", "1024x768", "1280x720", "1360x768", "1920x1080", "2048×1152", "3200×2048", "4096×2160"};
    public static final int[][] SCREEN_OPTIONTS_INT = {{600, 600}, {800, 800}, {1024, 768}, {1280, 720}, {1360, 768}, {1920, 1080}, {2048, 1152}, {3200, 2048}, {4056, 2160}};
    public static final String[] FPS_OPTIONS_STR = {"30 Hz", "60 Hz", "144 Hz", "240 Hz", "320 Hz"};
    public static final int[] FPS_OPTIONS_INT = {30, 60, 144, 240, 320};
}
