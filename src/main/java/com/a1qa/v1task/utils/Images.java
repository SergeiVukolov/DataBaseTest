package com.a1qa.v1task.utils;

import aquality.selenium.browser.AqualityServices;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Images {

    public boolean compareTwoPhoto(String pathNameOfFirstImage, String pathNameOfSecondImage) {
        boolean flag = false;
        try {
            ImagePlus imp1 = IJ.openImage(pathNameOfFirstImage);
            ImagePlus imp2 = IJ.openImage(pathNameOfSecondImage);
            ImageConverter ic1 = new ImageConverter(imp1);
            ic1.convertToGray8();
            ImageConverter ic2 = new ImageConverter(imp2);
            ic2.convertToGray8();
            ImageProcessor diff = imp1.getProcessor().duplicate();
            double numPixels = diff.getWidth() * diff.getHeight();
            double nonZeroPixels = diff.getStatistics().histogram[0];
            double percentNonZero = nonZeroPixels / numPixels * 100;
            double threshold = 95.0;
            if (percentNonZero < threshold) {
                flag = true;
            } else {
                flag = false;
            }
            imp1.close();
            imp2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public byte[] createByteFromPng(String pathToScreenShot) {
        try {
            File file = new File(pathToScreenShot);
            BufferedImage image = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getScreenShot(String path) {
        File scrFile = AqualityServices.getBrowser().getDriver().getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
