package com.santaellamorenofrancisco.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.santaellamorenofrancisco.model.Image;
import com.santaellamorenofrancisco.model.ImageMessage;
import com.santaellamorenofrancisco.service.ImageService;
import com.santaellamorenofrancisco.utils.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("/image")
public class ImageController {
	// Inyectamos el servicio
	
		@Autowired
		ImageService service;

		private static final String PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)";

		/**
		 * Método que sirve para guardar Files en la carpeta root y guardar los datos de
		 * los Files en la base de datos
		 * 
		 * @param files es el archivo que vamos a guardar
		 * @return una respuesta 200 con un mensaje diciendo que nuestro archivo con su
		 *         nombre original se ha subido correctamente o una respuesta 400 si ha
		 *         habído algun error
		 */
		@PostMapping("/upload")
		public ResponseEntity<ImageMessage> uploadFiles(@RequestParam("files") MultipartFile files,
				@RequestParam("product_id") Long product_id) {
			String message = "";
			// MultipartFile[]
			try {

				Pattern pattern = Pattern.compile(PATTERN);
				Matcher matcher = pattern.matcher(files.getOriginalFilename());

				if (matcher.matches()) {
					String uniquename = ImageUtils.uniqueFileName();
					System.out.println(uniquename);
					service.save(files, uniquename);
					service.saveDatabase(files, uniquename, product_id);
					message = "El archivo:" + files.getOriginalFilename();
					return ResponseEntity.status(HttpStatus.OK).body(new ImageMessage(message));

				} else {
					message = "El archivo que has subido no tiene una extensión valida";
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageMessage(message));
				}
				// falta control de errores por si un archivo ya existe y por si excede el
				// tamaño máximo

			} catch (Exception e) {
				List<String> fileNames = new ArrayList<>();
				System.out.println(fileNames.get(0).toString());
				message = "Fallo al subir los archivos";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageMessage(message));
			}
		}

		/**
		 * Método que trae una lista de objetos
		 * 
		 * @return devuelve una respuesta 200 con los objetos si se han encontrado y si no
		 *         se han encontrado una respuesta 400
		 */
		@GetMapping("/files")
		public ResponseEntity<List<Object>> getListFiles() {
			List<Object> fileInfos = service.loadAll().map(path -> {
				String filename = path.getFileName().toString();
				String url = MvcUriComponentsBuilder
						.fromMethodName(ImageController.class, "getFile", path.getFileName().toString()).build().toString();
				return new File(filename, url);
			}).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
		}

	

		/**
		 * Método que sirve para buscar un imagen en específico buscandolo por su nombre
		 * único
		 * 
		 * @param filename es el nombre único que vamos a usar para buscar el archivo en
		 *                 concreto
		 * @return devuelve una respuesta 200 con el archivo y si no ha sido válida la
		 *         petición una respuesta 400
		 */
		@GetMapping("/files/{filename:.+}")
		public ResponseEntity<Resource> getFile(@PathVariable String filename) {
			Resource file = service.load(filename);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		}

		/**
		 * Método que sirve para borrar una imagen por su nombre único
		 * 
		 * @param filename es el nombre único del archivo
		 * @return devuelve una respuesta 200 en la que pone que el archivo se ha
		 *         borrado correctamente si la petición no es válida devuelve una
		 *         respuesta 400 en la que pone que no se ha podido borrar el archivo
		 */
		@DeleteMapping("/delete/{filename:.+}")
		public ResponseEntity<ImageMessage> deleteFile(@PathVariable String filename) {
			String message = "";
			try {
				message = service.deleteFile(filename);
				return ResponseEntity.status(HttpStatus.OK).body(new ImageMessage(message));
			} catch (Exception e) {

				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageMessage(message));
			}
		}

		
		/**
		 * Metodo que trae la información de las imagenes desde la base de datos
		 * @return devuelve una lista de todas las imagenes de la base de datos
		 */
		@GetMapping("/filesfromdatabase")
		public ResponseEntity<List<Image>> getFilesFromDatabase() {
			try {
				List<Image> filelist = service.getFilesFromDatabase();
				return new ResponseEntity<List<Image>>(filelist, new HttpHeaders(), HttpStatus.OK);

			} catch (Exception e) {
				List<Image> filelist = new ArrayList<Image>();
				return new ResponseEntity<List<Image>>(filelist, new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		}
		
		
		@CrossOrigin(origins = "http://localhost:8080")
		@GetMapping("imgurl/{id}")
		public ResponseEntity<Image> getImgByProductId(@PathVariable Long id) {
			try {
				Image image = service.getImgByProductId(id);
				return new ResponseEntity<Image>(image, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {

				return new ResponseEntity<Image>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		}
}
