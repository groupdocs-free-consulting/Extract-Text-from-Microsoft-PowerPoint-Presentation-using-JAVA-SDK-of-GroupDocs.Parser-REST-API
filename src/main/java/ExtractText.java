import java.io.File;

import com.groupdocs.cloud.parser.api.FileApi;
import com.groupdocs.cloud.parser.api.InfoApi;
import com.groupdocs.cloud.parser.api.ParseApi;
import com.groupdocs.cloud.parser.client.ApiException;
import com.groupdocs.cloud.parser.client.Configuration;
import com.groupdocs.cloud.parser.model.FileInfo;
import com.groupdocs.cloud.parser.model.Format;
import com.groupdocs.cloud.parser.model.FormatsResult;
import com.groupdocs.cloud.parser.model.TextOptions;
import com.groupdocs.cloud.parser.model.TextResult;
import com.groupdocs.cloud.parser.model.requests.TextRequest;
import com.groupdocs.cloud.parser.model.requests.UploadFileRequest;

public class ExtractText {

	public static void main(String[] args) {
		// TODO: Get your AppSID and AppKey at https://dashboard.groupdocs.cloud (free
		// registration is required).
		String appSid = "xxxxxxx-xxxxx-xxxxx-xxxxxxxxxxxxxx";
		String appKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

		// SupportedFormats(appSid, appKey);
		UploadFile(appSid, appKey);
		ParseText(appSid, appKey);

	}

	// Parse Text of the File
	public static void ParseText(String appSid, String appKey) {

		Configuration configuration = new Configuration(appSid, appKey);

		ParseApi parseApi = new ParseApi(configuration);
		try {
			TextOptions options = new TextOptions();
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFilePath("Temp/three-slides.pptx");
			options.setFileInfo(fileInfo);
			TextRequest request = new TextRequest(options);

			// Parse Text
			TextResult result = parseApi.text(request);
			System.out.println(result.getText());

		} catch (ApiException e) {
			System.err.println("Failed to upuload file");
			e.printStackTrace();
		}

	}

	// Upload File to Cloud Storage
	public static void UploadFile(String appSid, String appKey) {

		Configuration configuration = new Configuration(appSid, appKey);

		FileApi fileApi = new FileApi(configuration);

		try {
			// Path where to upload including filename and extension
			String path = "Temp/three-slides.pptx";
			// File to upload
			File file = new File("C:/Temp/three-slides.pptx");
			// Storage name, GroupDocs Cloud is default storage
			String storage = null;

			UploadFileRequest request = new UploadFileRequest(path, file, storage);
			fileApi.uploadFile(request);
		} catch (ApiException e) {
			System.err.println("Failed to upuload file");
			e.printStackTrace();
		}

	}

	public static void SupportedFormats(String appSid, String appKey) {

		Configuration configuration = new Configuration(appSid, appKey);

		InfoApi infoApi = new InfoApi(configuration);

		try {
			FormatsResult response = infoApi.getSupportedFileFormats();
			for (Format format : response.getFormats()) {
				System.out.println(format.getFileFormat());
			}
		} catch (ApiException e) {
			System.err.println("Failed to get supported file formats");
			e.printStackTrace();
		}
	}

}
