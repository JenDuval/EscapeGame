package com.escapegame;

import com.escapegame.configuration.Settings;
import com.escapegame.game.Mod;
//import com.escapegame.tools.Menu;

public class Main {
    public static void main(String[] args) {
        Settings.readConf(1);
        Mod.menu();
    }
}