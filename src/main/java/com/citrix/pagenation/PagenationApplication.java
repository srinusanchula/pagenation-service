package com.citrix.pagenation;

import com.citrix.pagenation.domain.Device;
import com.citrix.pagenation.repository.DeviceRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class PagenationApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(PagenationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PagenationApplication.class, args);
	}

	@Autowired
	private DeviceRepository deviceRepository;

	@Override
	public void run(String... strings) throws Exception {
		logger.info("Delete all devices");
		deviceRepository.deleteAll();
		logger.info("Delete all devices successful");

		logger.info("Insert all devices");
		for(int i = 1; i < 101; i++) {
			Device device = new Device(
					i,
					RandomStringUtils.randomNumeric(5),
					RandomStringUtils.randomAlphanumeric(32).toUpperCase(),
					getRandomPlatform(),
					getRandomByod(),
					getRandomVersion(),
					getRandomUser()
			);

			deviceRepository.save(device);
		}
		logger.info("Insert all devices successful");
	}

	final String[] platforms = {"iOS", "Android", "Chrome", "rPi", "MacOS", "Windows", "AFW"};
	final Random random1 = new Random();
	private String getRandomPlatform() {
		return platforms[random1.nextInt(platforms.length)];
	}

	final char[] userprefixes = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z'};
	final Random random2 = new Random();
	private String getRandomUser() {
		return "user-" + userprefixes[random2.nextInt(userprefixes.length)];
	}

	final String[] versions = {"1.0", "2.0", "3.0", "4.0", "5.0"};
	final Random random3 = new Random();
	private String getRandomVersion() {
		return versions[random3.nextInt(versions.length)];
	}

	final boolean[] byods = {true, false};
	final Random random4 = new Random();
	private boolean getRandomByod() {
		return byods[random4.nextInt(byods.length)];
	}
}
