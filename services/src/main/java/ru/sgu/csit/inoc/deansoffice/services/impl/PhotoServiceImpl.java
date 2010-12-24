package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.domain.Photo;
import ru.sgu.csit.inoc.deansoffice.services.PhotoService;

import java.io.*;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 24.12.10
 * Time: 13:30
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Override
    public String getFormat(Photo photo) {
        String header = new String(photo.getData(), 0, 10).trim();

        if (header.contains("PNG")) {
            return "png";
        } else if (header.contains("GIF89")) {
            return "gif";
        } else if (header.contains("BM")) {
            return "bmp";
        } else if (header.contains("JFIF")) {
            return "jpg";
        } else {
            return "wbmp";
        }
    }

    @Override
    public void saveToFile(Photo photo, String fileName) throws IOException {
        OutputStream outputStream = new FileOutputStream(fileName);

        outputStream.write(photo.getData());
        outputStream.close();
    }

    @Override
    public Photo loadFromFile(String fileName) throws IOException {
        File photoFile = new File(fileName);
        InputStream inputStream = new FileInputStream(photoFile);

        int lengthFile = (int) photoFile.length();
        byte[] data = new byte[lengthFile];

        for (int len = 1, off = 0; len > 0 && off < lengthFile; off += len) {
            len = inputStream.read(data, off, lengthFile - off);
        }

        Photo photo = new Photo();
        photo.setData(data);

        return photo;
    }
}
