package com.hmkcode.spring.mvc.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ar.edu.itba.it.paw.domain.images.SGCategoryImage;
import ar.edu.itba.it.paw.domain.images.SGCategoryImageRepo;
import ar.edu.itba.it.paw.domain.images.SGColorImage;
import ar.edu.itba.it.paw.domain.images.SGColorImageRepo;
import ar.edu.itba.it.paw.domain.images.SGImage;
import ar.edu.itba.it.paw.domain.images.SGImageRepo;
import ar.edu.itba.it.paw.domain.products.Category;
import ar.edu.itba.it.paw.domain.products.Product;
import ar.edu.itba.it.paw.domain.products.ProductColor;

import com.hmkcode.spring.mvc.model.FileMeta;

@Controller
@RequestMapping("/controller")
public class FileController {
	// TIENE QUE EXISTIR
	private static final String IMAGEPATHProd = "/sgimages/products/";
	private static final String IMAGEPATHCat = "/sgimages/categories/";
	private static final String IMAGEPATHColor = "/sgimages/colors/";

	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	FileMeta fileMeta = null;

	private SGImageRepo sgImageRepo;
	private SGCategoryImageRepo sgCatImageRepo;
	private SGColorImageRepo sgColorImageRepo;

	@Autowired
	ServletContext context;

	@Autowired
	public FileController(SGImageRepo sgImageRepo,
			SGCategoryImageRepo sgCatImageRepo,
			SGColorImageRepo sgColorImageRepo) {
		this.sgImageRepo = sgImageRepo;
		this.sgCatImageRepo = sgCatImageRepo;
		this.sgColorImageRepo = sgColorImageRepo;
	}

	/***************************************************
	 * URL: /rest/controller/upload upload(): receives files
	 * 
	 * @param request
	 *            : MultipartHttpServletRequest auto passed
	 * @param response
	 *            : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value = "/uploadprod", method = RequestMethod.POST)
	public @ResponseBody
	LinkedList<FileMeta> uploadprod(@RequestParam("id") Product product,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		String webInfPath = context.getRealPath("webapp");

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());

			System.out.println(mpf.getOriginalFilename() + " uploaded! "
					+ files.size());
			if (mpf.getContentType().contains("image")) {
				// 2.2 if files > 10 remove the first from the list
				if (files.size() >= 10)
					files.pop();

				// 2.3 create new fileMeta
				fileMeta = new FileMeta();
				fileMeta.setFileName(mpf.getOriginalFilename());
				fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
				fileMeta.setFileType(mpf.getContentType());

				try {
					fileMeta.setBytes(mpf.getBytes());

					// copy file to local disk (make sure the path
					// "e.g. D:/temp/files" exists)
					String directurl = IMAGEPATHProd + product.getId() + "/";
					String url = webInfPath + directurl;
					File m = new File(url);
					m.mkdirs();
					url += mpf.getOriginalFilename();
					FileCopyUtils.copy(mpf.getBytes(),
							new FileOutputStream(url));
					SGImage sgImage = new SGImage(product, directurl,
							mpf.getOriginalFilename(), mpf.getSize() / 1024
									+ " Kb");
					sgImageRepo.add(sgImage);
					product.addPhoto(sgImage);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 2.4 add to files
				files.add(fileMeta);
			}
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;

	}

	@RequestMapping(value = "/uploadcolor", method = RequestMethod.POST)
	public @ResponseBody
	LinkedList<FileMeta> uploadcolor(@RequestParam("id") ProductColor color,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		String webInfPath = context.getRealPath("webapp");

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! "
					+ files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();
			if (mpf.getContentType().contains("image")) {
				// 2.3 create new fileMeta
				fileMeta = new FileMeta();
				fileMeta.setFileName(mpf.getOriginalFilename());
				fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
				fileMeta.setFileType(mpf.getContentType());

				try {
					fileMeta.setBytes(mpf.getBytes());

					// copy file to local disk (make sure the path
					// "e.g. D:/temp/files" exists)
					String directurl = IMAGEPATHColor + color.getId() + "/";
					String url = webInfPath + directurl;
					File m = new File(url);
					m.mkdirs();
					url += mpf.getOriginalFilename();
					FileCopyUtils.copy(mpf.getBytes(),
							new FileOutputStream(url));
					SGColorImage sgColorImage = new SGColorImage(color,
							directurl, mpf.getOriginalFilename(), mpf.getSize()
									/ 1024 + " Kb");
					sgColorImageRepo.add(sgColorImage);
					color.addPhoto(sgColorImage);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 2.4 add to files
				files.add(fileMeta);
			}
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;

	}

	@RequestMapping(value = "/uploadcat", method = RequestMethod.POST)
	public @ResponseBody
	LinkedList<FileMeta> uploadcat(@RequestParam("id") Category category,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		String webInfPath = context.getRealPath("webapp");

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! "
					+ files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();
			if (mpf.getContentType().contains("image")) {
				// 2.3 create new fileMeta
				fileMeta = new FileMeta();
				fileMeta.setFileName(mpf.getOriginalFilename());
				fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
				fileMeta.setFileType(mpf.getContentType());

				try {
					fileMeta.setBytes(mpf.getBytes());

					// copy file to local disk (make sure the path
					// "e.g. D:/temp/files" exists)
					String directurl = IMAGEPATHCat + category.getId() + "/";
					String url = webInfPath + directurl;
					File m = new File(url);
					m.mkdirs();

					url += mpf.getOriginalFilename();

					FileCopyUtils.copy(mpf.getBytes(),
							new FileOutputStream(url));
					SGCategoryImage sgCatImage = new SGCategoryImage(category,
							directurl, mpf.getOriginalFilename(), mpf.getSize()
									/ 1024 + " Kb");
					sgCatImageRepo.add(sgCatImage);
					category.addPhoto(sgCatImage);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 2.4 add to files
				files.add(fileMeta);
			}
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;

	}

	/***************************************************
	 * URL: /rest/controller/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String value) {
		FileMeta getFile = files.get(Integer.parseInt(value));
		try {
			response.setContentType(getFile.getFileType());
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ getFile.getFileName() + "\"");
			FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
