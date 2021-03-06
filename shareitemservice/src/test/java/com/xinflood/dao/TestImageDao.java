package com.xinflood.dao;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.common.io.ByteStreams;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by xinxinwang on 11/16/14.
 */
public class TestImageDao {

    private ImageDao imageDao;

    @Before
    public void setup() {
        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
        imageDao = new S3ImageDao(s3client, "share-images-xinflood");
    }

    @Test
    public void testRead() throws IOException {
        byte[] img = imageDao.getImage("testImage");
        assertNotNull(img);
    }

    @Test
    public void testWrite() throws IOException {
        String imageKey = "testImage";
        assertTrue(imageDao.putImage(imageKey, ByteStreams.toByteArray(new FileInputStream(new File("/Users/xinxinwang/Downloads/delete.png")))));
    }

}
