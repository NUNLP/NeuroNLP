package edu.northwestern.fsm.service

import com.google.common.io.Resources
import org.apache.commons.io.Charsets
import org.apache.uima.internal.util.CommandLineParser
import org.apache.uima.resource.RelativePathResolver
import org.apache.uima.tools.cvd.MainFrame
import org.apache.uima.tools.images.Images

import javax.swing.*
import java.lang.reflect.InvocationTargetException

class SDHExtractionCVD {

    /**
     * Hidden constructor
     */
    private SDHExtractionCVD() {
        super()
    }

    /**
     * Create the GUI
     * @return
     */
    static MainFrame createMainFrame() {
        final MainFrame frame = new MainFrame(null)
        ImageIcon icon = Images.getImageIcon(Images.MICROSCOPE)
        if (icon != null) {
            frame.setIconImage(icon.getImage())
        }
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                void run() {
                    frame.pack()
                    frame.setVisible(true)
                }
            })
        } catch (InterruptedException e) {
            e.printStackTrace()
        } catch (InvocationTargetException e) {
            e.printStackTrace()
        }
        return frame
    }

    /**
     * Run the CVD, pre-loading the pipeline
     * @param args
     */
    static void main(String[] args) {
        try {
            MainFrame frame = createMainFrame()
            URL url = Resources.getResource('descriptors/SDHPipeline.xml')
            File tempFile = File.createTempFile('Pipeline', '.xml')
            tempFile.write(Resources.toString(url, Charsets.UTF_8))
            tempFile.deleteOnExit()
            frame.loadAEDescriptor(tempFile)
        } catch (Exception e) {
            e.printStackTrace()
            System.exit(1)
        }
    }

}

