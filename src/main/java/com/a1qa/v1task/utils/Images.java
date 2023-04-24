package com.a1qa.v1task.utils;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.OutputType;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class Images {
//    static {
//        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
//    }
//
//    public boolean compareTwoPhoto(String pathNameOfFirstImage, String pathNameOfSecondImage) {
//        boolean flag = true;
//        try {
//            Mat image1 = Highgui.imread(pathNameOfFirstImage);
//            Mat image2 = Highgui.imread(pathNameOfSecondImage);
//            Mat grayImage1 = new Mat();
//            Mat grayImage2 = new Mat();
//            Imgproc.cvtColor(image1, grayImage1, Imgproc.COLOR_BGR2GRAY);
//            Imgproc.cvtColor(image2, grayImage2, Imgproc.COLOR_BGR2GRAY);
//            Mat diffImage = new Mat();
//            Core.absdiff(grayImage1, grayImage2, diffImage);
//            Mat threshImage = new Mat();
//            Imgproc.threshold(diffImage, threshImage, 0, 255, Imgproc.THRESH_BINARY);
//            double sum = Core.sumElems(threshImage).val[0];
//            double threshold = 10000.0;
//            if (!(sum < threshold)) {
//                flag = false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }

    public Blob createBlobFromPng(String pathToScreenShot) {
        try {
            BufferedImage image = ImageIO.read(new File(pathToScreenShot));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return new javax.sql.rowset.serial.SerialBlob(imageBytes);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void getScreenShot() {
        File scrFile = AqualityServices.getBrowser().getDriver().getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(JsonHelper.getValueFromJson("pathToScreenShot")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
