package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.AdminMedicalManager;
import com.projet.BackendPfe.Entity.AutoDetection;
import com.projet.BackendPfe.Entity.Consultation;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.AutoDetectionRepository;
import com.projet.BackendPfe.repository.ConsultationRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequestAdmin;
import com.projet.BackendPfe.request.RegisterRequestExpert;
import com.projet.BackendPfe.services.AdminMedicalManagerService;
import com.projet.BackendPfe.services.ExpertService;
import com.projet.BackendPfe.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adminMedical")
public class AdminMedicalManagerController {

	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	AdminMedicalManagerRepository repository;
	@Autowired	private  AdminMedicalManagerService service  ;
	@Autowired	private ExpertService expertService ;
	@Autowired	AdminMedicalManagerRepository repositoryAdmin;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	@Autowired	AutoDetectionRepository repoAuto;
@Autowired GeneralisteRepository genRepository ;
@Autowired ConsultationRepository consultationRepository ;
	@PostMapping("/login")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest data) {
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword()));	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getNomPrenom(), 
												 userDetails.getEmail() ,  
												 userDetails.getRole()
											));}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequestAdmin signUpRequest) {
		if (repository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));	}
		
		if (repository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));
		}
		AdminMedicalManager admin = new AdminMedicalManager(signUpRequest.getUsername(), 
				 signUpRequest.getEmail(),
				 signUpRequest.getNomPrenom(),
				 encoder.encode(signUpRequest.getPassword()),
						 signUpRequest.getImage(), LocalDate.now(), "Medical Manager" , 
						 signUpRequest.getDiplome() ,signUpRequest.getReservePassword() );
		repository.save(admin);

		return ResponseEntity.ok(new Message("Admin  registered successfully!"));
	}	  
	
	@PutMapping("update/{id}")
	  public ResponseEntity<?> updateAdmin(@PathVariable("id") long id, @RequestBody AdminMedicalManager Admin) {
		    System.out.println("Update Utilisateur with ID = " + id + "...");
		    Optional<AdminMedicalManager> UtilisateurInfo = repository.findById(id);
		    AdminMedicalManager utilisateur = UtilisateurInfo.get();
		    if (repository.existsByUsername(Admin.getUsername())) {
		    	if(repository.findById(id).get().getId() != (repository.findByUsername(Admin.getUsername()).getId())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Username is already taken!"));
			}
		    }
			if (repository.existsByEmail(Admin.getEmail())) {
				if(repository.findById(id).get().getId() != (repository.findByEmail(Admin.getEmail()).getId())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Email is already in use!"));
			}
			}


		    	utilisateur.setUsername(Admin.getUsername());
		    	 utilisateur.setEmail(Admin.getEmail());
		    	
		      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
		    } 
		  
	  
	  @PutMapping("/updateAdminMedical/{id}")
		 public ResponseEntity<?> updateAdmin(@PathVariable("id") long id, @RequestBody Generaliste Utilisateur) {

	  Optional<AdminMedicalManager> UtilisateurInfo = repository.findById(id);
	  AdminMedicalManager utilisateur = UtilisateurInfo.get();
	    if (repository.existsByUsername(Utilisateur.getUsername())) {
	    	if(repository.findById(id).get().getId() != (repository.findByUsername(Utilisateur.getUsername()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (repository.existsByEmail(Utilisateur.getEmail())) {
			if(repository.findById(id).get().getId() != (repository.findByEmail(Utilisateur.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));
		}
		}

	  
	    	utilisateur.setUsername(Utilisateur.getUsername());
	    	 utilisateur.setEmail(Utilisateur.getEmail());
	
	      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
	    } 
		@GetMapping("/{id}")
		public AdminMedicalManager get(@PathVariable("id") long  idAdmin) {
			
			AdminMedicalManager admin =  repository.findById(idAdmin).get();
			return admin;
		}
		@GetMapping("getEspaceStockage/{idGeneraliste}")
		public long getEspaceStockage(@PathVariable("idGeneraliste") long idGeneraliste) {
			
			Generaliste generaliste = genRepository.findById(idGeneraliste).get();
			List<Consultation> consultations = consultationRepository.findAll();
			List<Consultation> liste = new ArrayList<Consultation>() ;
			long espaceStockage = 0 ; 
			for(Consultation cons : consultations) {
				if(cons.getGeneraliste().getId()==generaliste.getId()) {
					liste.add(cons);
				}
				
				for(Consultation c :liste) {
					espaceStockage =espaceStockage +c.getSizeConsultaion() ;
				}
			}
			return espaceStockage/(1000000) ; 
		}
		 @PutMapping( "/updateAdminSansUsername/{id}")
		 public void updateUser (@PathVariable ("id") long id , @RequestBody AdminMedicalManager admin ) 
		 {
	    	
	       AdminMedicalManager admin1 = repository.findById(id).get();
	      admin1.setNomPrenom(admin.getNomPrenom());
	       repository.save(admin1);
	        	
		 }
		 @GetMapping( path="/getImage/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
			public byte[] getImage(@PathVariable("id") long id)throws Exception{
			  AdminMedicalManager admin = repository.findById(id).get();
	          return  service.getImageAdminMedicalManager(admin.getId());
				
			}
		  @PutMapping("/updateImage/{id}")
			public String updateImage1DD(@PathVariable("id") long id  , @RequestParam("file") MultipartFile file ) throws IOException {
					service.updateImageAdminMedicalManager(id,  file);
					
				return "Done pour image2  Droite!!!!" ; 
			}
		  
		  @GetMapping("getNbrMaladie1")
			public long maladie1() {
				List<AutoDetection> res =new ArrayList<AutoDetection>();
				List<AutoDetection> consultations = repoAuto.findAll();
				for(AutoDetection cons : consultations) {
					if(cons.getMaladieDroite()!= null) {
					if(cons.getMaladieDroite().equals("Le trou maculaire")) {
						res.add(cons);
					}
					}
					if(cons.getMaladieGauche()!= null) {
						if(cons.getMaladieGauche().equals("Le trou maculaire")) {
							if(!(res.contains(cons))) {
								res.add(cons);
							}
				}
				
					}
				
				}
				return res.size() ; 
			}  
		  @GetMapping("getNbrMaladie2")
			public long maladie2() {
				List<AutoDetection> res =new ArrayList<AutoDetection>();
				List<AutoDetection> consultations = repoAuto.findAll();
				for(AutoDetection cons : consultations) {
					if(cons.getMaladieDroite()!= null) {
					if(cons.getMaladieDroite().equals("Le décollement de rétine")) {
						res.add(cons);
					}
					}
					if(cons.getMaladieGauche()!= null) {
						if(cons.getMaladieGauche().equals("Le décollement de rétine")) {
							if(!(res.contains(cons))) {
								res.add(cons);
							}
				}
				
					}
				
				}
				return res.size() ; 
			}  
		  @GetMapping("getNbrMaladie3")
			public long maladie3() {
				List<AutoDetection> res =new ArrayList<AutoDetection>();
				List<AutoDetection> consultations = repoAuto.findAll();
				for(AutoDetection cons : consultations) {
					if(cons.getMaladieDroite()!= null) {
					if(cons.getMaladieDroite().equals("rétinopathie diabétique")) {
						res.add(cons);
					}
					}
					if(cons.getMaladieGauche()!= null) {
						if(cons.getMaladieGauche().equals("rétinopathie diabétique")) {
							if(!(res.contains(cons))) {
								res.add(cons);
							}
				}
				
					}
				
				}
				return res.size() ; 
			}  
		  @GetMapping("getNbrMaladie4")
			public long maladie4() {
				List<AutoDetection> res =new ArrayList<AutoDetection>();
				List<AutoDetection> consultations = repoAuto.findAll();
				for(AutoDetection cons : consultations) {
					if(cons.getMaladieDroite()!= null) {
					if(cons.getMaladieDroite().equals("DMLA")) {
						res.add(cons);
					}
					}
					if(cons.getMaladieGauche()!= null) {
						if(cons.getMaladieGauche().equals("DMLA")) {
							if(!(res.contains(cons))) {
								res.add(cons);
							}
				}
				
					}
				
				}
				return res.size() ; 
			}  
		  @GetMapping("getNbrSain")
			public long getSain() {
				List<AutoDetection> res =new ArrayList<AutoDetection>();
				List<AutoDetection> consultations = repoAuto.findAll();
				for(AutoDetection cons : consultations) {
					if(cons.getMaladieDroite()!= null) {
					if(cons.getMaladieDroite().equals("Sain")) {
						res.add(cons);
					}
					}
					if(cons.getMaladieGauche()!= null) {
						if(cons.getMaladieGauche().equals("Sain")) {
							if(!(res.contains(cons))) {
								res.add(cons);
							}
				}
				
					}
				
				}
				return res.size() ; 
			}  
 }

