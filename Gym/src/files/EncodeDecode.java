package files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class EncodeDecode {

	// 파일 인코드
	public String encodeImage(File file) {
		String code = null;
		Path path = Paths.get(file.getPath());
		try {
			byte[] readAllBytes = Files.readAllBytes(path);
			Encoder encoder = Base64.getEncoder();
			code = encoder.encodeToString(readAllBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	// 파일 디코드
	public byte[] decode(String data) {
		Decoder decoder = Base64.getDecoder();
		System.out.println(data);
		byte[] decode = decoder.decode(data);
		System.out.println(decode);
		return decode;
	}
}
