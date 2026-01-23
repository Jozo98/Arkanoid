package main;


import utility.SaveManager;

import java.io.IOException;

public class Levels {

    public static boolean[][] levels =
            {
                    {
                            false, false, false, false, false, true, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false
                    },

                    {
                            true, true, true, true, true, true, true, true, true, true, true,
                            true, true, true, true, true, true, true, true, true, true, true,
                            true, true, true, true, true, true, true, true, true, true, true,
                            true, true, true, true, true, true, true, true, true, true, true,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false
                    },

                    {
                            false, false, false, false, true, true, true, false, false, false, false,
                            false, false, false, true, true, true, true, true, false, false, false,
                            false, false, true, true, true, true, true, true, true, false, false,
                            false, true, true, true, true, true, true, true, true, true, false,
                            false, true, true, true, true, true, true, true, true, true, false,
                            false, true, true, true, true, true, true, true, true, true, false,
                            false, true, true, true, true, true, true, true, true, true, false,
                            false, true, true, true, true, true, true, true, true, true, false,
                            false, true, true, true, true, true, true, true, true, true, false,
                            false, false, true, true, true, true, true, true, true, false, false,
                            false, false, false, true, true, true, true, true, false, false, false,
                            false, false, false, false, true, true, true, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false, false
                    },

                    {
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true,
                            true, false, true, false, true, false, true, false, true, false, true
                    },

                    {
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false,
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false,
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false,
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false,
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false,
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false,
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false,
                            true, false, true, false, true, false, true, false, true, false, true,
                            false, true, false, true, false, true, false, true, false, true, false
                    },

                    {
                            false, false, true,  true,  true,  true,  true,  true, true, false, false,  // Head top
                            false, true,  true,  false, false, true,  false, false, true, true, false, // Head/eyes
                            true,  true,  true,  true,  true,  true,  true,  true,  true, true, true,   // Upper body
                            true,  false, true,  true,  true,  true,  true,  true, true, false, true,  // Upper arms
                            true,  true,  true,  true,  true,  true,  true,  true, true, true, true,   // Body
                            true,  true,  false, true,  true,  true,  true,  true, false, true, true,  // Body details
                            true,  true,  true,  true,  true,  true,  true,  true, true, true, true,   // Lower body
                            true,  false, false, true,  true,  true,  true,  true, false, false, true, // Arms down
                            true,  false, false, false, true,  true,  true, false, false, false, true, // Legs start
                            true,  false, false, false, true,  true,  true, false, false, false, true, // Legs middle
                            false, true,  false, false, false, true, false, false, false, true, false, // Feet
                            false, true,  false, false, false, true, false, false, false, true, false, // Feet
                            false, false, true,  false, false, false, false, false, true, false, false, // Tip of legs
                            false, false, true,  false, false, false, false, false, true, false, false,
                            false, false, false, true,  false, false, false, true,  false, false, false,
                            false, false, false, false, true,  true,  true, false, false, false, false
                    }

            };
}