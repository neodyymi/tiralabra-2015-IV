/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarsa.commands;

/**
 * Interface for commands to be run in the text-ui.
 *
 * @author vrsaari
 */
public interface Command {

    /**
     * Executes the command.
     */
    public abstract void run();
}
