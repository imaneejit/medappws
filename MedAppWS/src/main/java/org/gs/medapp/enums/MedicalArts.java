package org.gs.medapp.enums;

public enum MedicalArts 
{
	AUDIOLOGIST			( 0, "Audiologist" ),
	ALLERGIST 			( 1, "Allergist" ),
	ANDROLOGIST			( 2, "Andrologist" ),
	ANESTHESIOLOGIST	( 3, "Anesthesiologist" ),
	CARDIOLOGIST		( 4, "Cardiologist" ),
	CARDIOSURGEON		( 5, "Cardiovascular Surgeon" ),
	CLINEUROPHY			( 6, "Clinical Neurophysiologist" ),
	DENTIST				( 7, "Dentist" ),
	DERMATOLOGIST		( 8, "Dermatologist" ),
	EMERDOC				( 9, "Emergency Doctors" ),
	ENDOCRINOLOGIST		( 10, "Endocrinologist" ),
	EPIDEMIOLOGIST		( 11, "Epidemiologist" ),
	ENTSPECIALIST		( 12, "ENT Specialist" ),
	FAMPRAC				( 13, "Family Practitioner" ),
	GASTROENTERO		( 14, "Gastroenterologist" ),
	GYNECOLOGIST		( 15, "Gynecologist" ),
	GENPSYCH			( 16, "General Psychiatrist" ),
	HEMATOLOGIST		( 17, "Hematologist" ),
	HEPATOLOGIST		( 18, "Hepatologist" ),
	INFDISSPEC			( 19, "Infectious Disease Specialist" ),
	INTMEDSPEC			( 20, "Internal Medicine Specialist" ),
	INTERNISTS			( 21, "Internists" ),
	MEDGENETIC			( 22, "Medical Geneticist" ),
	MICROBIO			( 23, "Microbiologist" ),
	NEONATOLOGIST		( 24, "Neonatologist" ),
	NEPHROLOGIST		( 25, "Nephrologist" ),
	NEUROLOGIST			( 26, "Neurologist" ),
	NEUROSURGEON		( 27, "Neurosurgeon" ),
	OBSTETRICIAN		( 28, "Obstetrician" ),
	ONCOLOGIST			( 29, "Oncologist" ),
	OPTHALMOLOGIST		( 30, "Opthalmologist" ),
	ORTHOSURG			( 31, "Orthopedic Surgeon" ),
	ORTHOPEDIST			( 32, "Orthopedist" ),
	PRIMATOLOGIST		( 33, "Primatologist" ),
	PALEPATHO			( 34, "Pale Pathologist" ),
	PARASITOLOGIST		( 35, "Parasitologist" ),
	PATHOLOGIST			( 36, "Pathologist" ),
	PEDIATRICIAN		( 37, "Pediatrician" ),
	PLASTICSURG			( 38, "Plastic Surgeon" ),
	PODIATRIST			( 39, "Podiatrist" ),
	PSYCHIATRIST		( 40, "Psychiatrist" ),
	PULMONOLOGIST		( 41, "Pulomonologist" ),
	RADIOLOGIST			( 42, "Radiologist" ),
	REPROENDOC			( 43, "Reproductive Endocrinologist" ),
	RHEUMATOLOGIST		( 44, "Rheumatologist" ),
	SURGEON				( 45, "Surgeon" ),
	THORONCO			( 46, "Thoraxic Oncologist" ),
	UROLOGIST			( 47, "Urologist" ),
	VETERINARIAN		( 48, "Veterinarian" );
	
	private Integer code;
	private String description;
	private MedicalArts( Integer code, String description )
	{
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode()
	{
		return code;
	}
	
	public String getDescription()
	{
		return description;
	}
}
