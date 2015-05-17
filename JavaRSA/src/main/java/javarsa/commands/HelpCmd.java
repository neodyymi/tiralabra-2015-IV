/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

import javarsa.functions.Help;

/**
 * In charge of displaying the help information to the text-ui.
 *
 * @author vrsaari
 */
public class HelpCmd implements Command {

    public HelpCmd() {
    }

    @Override
    public void run() {
        Help.help();
    }

}
