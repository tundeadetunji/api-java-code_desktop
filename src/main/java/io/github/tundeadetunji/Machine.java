package io.github.tundeadetunji;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Functions relating to the device.
 */
public final class Machine {
    /**
     * Reads text content from clipboard.
     *
     * @return
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public static Object getTextFromClipboard() throws IOException, UnsupportedFlavorException {
        return Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    /**
     * Writes text to clipboard.
     *
     * @param text
     */
    public static void sendTextToClipboard(String text) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(text),
                        null
                );
    }

    /**
     * Reads text content from clipboard.
     *
     * @return
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    public static Object readTextFromClipboard() throws IOException, UnsupportedFlavorException {
        return getTextFromClipboard();
    }

    /**
     * Writes text to clipboard.
     *
     * @param text
     */
    public static void writeTextToClipboard(String text) {
        sendTextToClipboard(text);
    }

}
