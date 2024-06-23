package io.github.tundeadetunji;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CameraAndMic {

    public static class camera{
        /**
         * Takes photo using PC's webcam.
         * new CameraAndMic.camera().snap("snap.jpg", ImageUtils.FORMAT_JPG, 2024, 768);
         * @param filename full file path to target file
         * @param format e.g. JPG - Try using ImageUtils, e.g. ImageUtils.FORMAT_JPG
         * @param width e.g. 1366
         * @param height e.g. 768
         * @throws IOException
         */
        public void snap(String filename, String format, int width, int height) throws IOException {
            Webcam cam = Webcam.getDefault();
            cam.setCustomViewSizes(new Dimension(width, height));
            cam.setCustomViewSizes(new Dimension(width, height));
            cam.setViewSize(new Dimension(width, height));
            cam.open();
            //BufferedImage image = cam.getImage();
            ImageIO.write(cam.getImage(), format, new File(filename));
        }
    }

    /**
     * Records audio with computer's mic
     * new CameraAndMic.mic(5, "file.mp3").record();
     */
    public static class mic {
        private static long RECORD_TIME;
        private File wavFile;

        private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
        private TargetDataLine line;

        public mic(long recordTimeInSeconds, String fullFilePath){
            RECORD_TIME = recordTimeInSeconds * 1000;
            wavFile = new File(fullFilePath.trim().length() > 0 ? fullFilePath : "Recording.wav");
        }

        private AudioFormat getAudioFormat() {
            return new AudioFormat(16000, 8, 2, true, true);
        }

        private void start() {
            try {
                AudioFormat format = getAudioFormat();
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

                if (!AudioSystem.isLineSupported(info)) {
                    System.exit(0);
                }
                line = (TargetDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();

                AudioInputStream ais = new AudioInputStream(line);
                AudioSystem.write(ais, fileType, wavFile);

            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        private void finish() {
            line.stop();
            line.close();
        }

        public void record(){
            Thread stopper = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    finish();
                }
            });

            stopper.start();
            this.start();
        }
    }
}
