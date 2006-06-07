package test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

// @todo document me!

/**
 * <code>DownloadExample</code>.
 * <p/>
 * User: rro
 * Date: 03.02.2006
 * Time: 18:26:37
 *
 * @author Roman R&auml;dle
 * @version $Id$
 * @since EcoBill 1.1
 */
public class DownloadExample extends JFrame {

	JTextField txtUrl;

	JButton btnDownload;

	JLabel lblThroughput;

	JProgressBar progressBar;

	boolean downloadInProgress;

	public DownloadExample() {
		super("DownloaderExample");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridLayout gridLayout = new GridLayout(2, 3);
		setLayout(gridLayout);

		txtUrl = new JTextField("http://rm.mirror.garr.it/mirrors/eclipse//technology/ajdt/31/update/ajdt_1.3_for_eclipse_3.1.zip");
		btnDownload = new JButton("download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downloadInProgress = true;
				new Thread(){
					public void run() {
						InputStream inputStream = null;
						OutputStream outputStream = null;
						try {
							String urlText = txtUrl.getText();
							URL url = new URL(urlText);
							URLConnection urlConnection = url.openConnection();
							progressBar.setMaximum(urlConnection.getContentLength());

							inputStream = urlConnection.getInputStream();
							String filePath = url.getFile();
							String fileName = filePath.substring(filePath.lastIndexOf('/'));
							outputStream = new FileOutputStream(new File(fileName));

							byte[] buffer = new byte[16384];
							int bytesRead = -1;
							int bytesWritten = 0;

							int bytesPerSecond = 0;
							long deltaTime = 0;
							long time = -System.currentTimeMillis();
							while((bytesRead = inputStream.read(buffer)) > 0){
								outputStream.write(buffer,0,bytesRead);
								bytesWritten +=bytesRead;
								outputStream.flush();
								progressBar.setValue(bytesWritten);

								bytesPerSecond += bytesRead;

                                if(deltaTime > 1000){
									lblThroughput.setText(bytesPerSecond/1024 + " kb/s");
									bytesPerSecond = 0;
									deltaTime = 0;
									time = -System.currentTimeMillis();
								}
								deltaTime += time + System.currentTimeMillis();
							}
							JOptionPane.showMessageDialog(DownloadExample.this,"Download completed!");

						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							if(outputStream != null){
								try {
									outputStream.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if(inputStream != null){
								try {
									inputStream.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

							downloadInProgress = false;
						}

					}
				}.start();
			}
		}
		);

		lblThroughput = new JLabel("0.0 kb");
		progressBar = new JProgressBar(0, 0);
		progressBar.setStringPainted(true);

		add(new JLabel("URL: "));
		add(txtUrl);
		add(btnDownload);

		add(lblThroughput);
		add(progressBar);

		pack();
		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DownloadExample();
//		try {
//			URLConnection connection = new URL(
//					"http://rm.mirror.garr.it/mirrors/eclipse//technology/ajdt/31/update/ajdt_1.3_for_eclipse_3.1.zip")
//					.openConnection();
//			System.out.println(connection.getContentLength());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}