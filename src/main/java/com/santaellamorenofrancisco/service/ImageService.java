package com.santaellamorenofrancisco.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.santaellamorenofrancisco.model.Image;
import com.santaellamorenofrancisco.repository.ImageRepository;
import com.santaellamorenofrancisco.repository.ProductRepository;
import com.santaellamorenofrancisco.utils.ImageUtils;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ImageService {

	@Autowired
	ImageRepository repository;

	ProductRepository productrepository;

	private final Path root = Paths.get("uploads");

	/**
	 * Metodo que inicializa el directorio en el que se guardan los archivos
	 */
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (Exception e) {
			throw new RuntimeException("No se puede inicializar la carpeta uploads");
		}
	}

	/**
	 * Metodo que guarda el archivo subido en la carpeta de destino
	 * 
	 * @param file       es el archivo que se va a guardar
	 * @param uniquename es nombre unico que se ha autogenerado
	 */
	public void save(MultipartFile file, String uniquename) {
		try {

			Files.copy(file.getInputStream(),
					this.root.resolve(uniquename + "." + ImageUtils.getExtension(file.getOriginalFilename())));
		} catch (IOException e) {
			throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
		}
	}

	/**
	 * Metodo que sirve para guardar un File en la base de datos, este archivo
	 * tendrá un nombre único
	 * 
	 * @param file       Lo usamoos para saber la extensión del archivo
	 * @param uniquename Es el nombre aleatorio y único que se ha generado y vamos a
	 *                   setear el nombre del File con dicho nombre
	 */
	public void saveDatabase(MultipartFile file, String uniquename, Long product_id) {
		Image uploadimage = new Image();
		try {
			uploadimage.setUrl(root.resolve(uniquename).toString());
			uploadimage.setOriginalname(file.getOriginalFilename());
			uploadimage.setUniquename(uniquename + "." + ImageUtils.getExtension(file.getOriginalFilename()));
			repository.insertFile(uploadimage.getOriginalname(), uploadimage.getUniquename(), uploadimage.getUrl(),
					product_id);
		} catch (Exception e) {
			throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
		}
	}

	/**
	 * Metodo para cargar un archivo
	 * 
	 * @param filename
	 * @return
	 */
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo ");
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	/**
	 * Metodo que sirve para borrar todos los archivos
	 */
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	/**
	 * Metodo que sirve para traer todos los archivos
	 * 
	 * @return devuelve una lista de File
	 */
	public List<Image> getAll() {
		return repository.findAll();
	}

	/**
	 * Metodo que trae los Datos de File desde la base de datos
	 * 
	 * @param filename es el nombre aleatorio que tiene el File
	 * @return devuelve un File
	 */
	public List<Image> getFilesFromDatabase() {
		return repository.getFilesFromDatabase();
	}

	/**
	 * Metodo que trae todas las rutas relativas de las imágenes
	 * 
	 * @return Un stream de rutas en las que se encuentras los File
	 */
	public Stream<Path> loadAll() {
		// Files.walk recorre nuestras carpetas (uploads) buscando los archivos
		// el 1 es la profundidad o nivel que queremos recorrer
		// :: Referencias a metodos
		// Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException("No se pueden cargar los archivos ");
		}
	}

	/**
	 * Borra un archivo especifico pasando su nombre
	 * 
	 * @param filename el nombre aleatorio del archivos
	 * @return String diciendo si se ha borrado o no, según si el resultado es
	 *         favorable o no lo es
	 */
	public String deleteFile(String filename) {
		try {
			// repository.deleteByName(filename);
			Files.deleteIfExists(this.root.resolve(filename));
			return "Borrado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error Borrando";
		}
	}
	
	/**
	 * Metodo que trae una imagen por el id de un producto
	 * @param id es el id del producto del que queremos traer su imagen
	 * @return devuelve una imagen
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Image getImgByProductId(Long id ) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				Image image = repository.getImgByProductId(id);
				if (image != null) {
					return image;
				} else {
					// logger.error("The Product doesn't exists in the database.");
					throw new Exception("El Product no existe");
				}
			} catch (IllegalArgumentException e) {
				// logger.error("IllegalArgumentException in the method getProductById: " + e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				// logger.error("Exception in the method getProductById: " + e);
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method getProductById id equals to
			// null.");
			throw new NullPointerException("El id es un objeto nulo");
		}
	}
}
