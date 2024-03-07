/**
 * Code generated by WINDEV Mobile Express - DO NOT MODIFY!
 * WINDEV Mobile Express object: Collection
 * Android class: GlobalProcedures
 * Version of wdjava.dll: 28.0.464.1
 */


package com.mycompany.projectadvogado.wdgen;


import com.mycompany.projectadvogado.*;
import fr.pcsoft.wdjava.core.types.*;
import fr.pcsoft.wdjava.core.*;
import fr.pcsoft.wdjava.core.application.*;
import fr.pcsoft.wdjava.net.http.*;
import fr.pcsoft.wdjava.core.poo.*;
import fr.pcsoft.wdjava.api.*;
import fr.pcsoft.wdjava.core.erreur.*;
/*Imports trouvés dans le code WL*/
/*Fin Imports trouvés dans le code WL*/



public class GWDCPGlobalProcedures extends WDCollProcAndroid
{

public WDProjet getProjet()
{
return GWDPProjectAdvogado.getInstance();
}

public IWDEnsembleElement getEnsemble()
{
return GWDPProjectAdvogado.getInstance();
}

protected String getNomCollection()
{
return "GlobalProcedures";
}
private final static GWDCPGlobalProcedures ms_instance = new GWDCPGlobalProcedures();
public final static GWDCPGlobalProcedures getInstance()
{
return ms_instance;
}

// Code de déclaration de GlobalProcedures
static public void init()
{
// 
ms_instance.initDeclarationCollection();



try
{

}
finally
{
finDeclarationCollection();

}
}




// Code de terminaison de GlobalProcedures
static public void term()
{
// 
ms_instance.initTerminaisonCollection();



try
{

}
finally
{
finTerminaisonCollection();

}
}



// Nombre de Procédures : 1

static public void fWD_getUser()
{
// PROCEDURE getUser()
ms_instance.initExecProcGlobale("getUser");



try
{

////////////////////////////////////////////////////////////////////////////
// Déclaration des variables locales au traitement
// (En WLangage les variables sont encore visibles après la fin du bloc dans lequel elles sont déclarées)
////////////////////////////////////////////////////////////////////////////
WDObjet vWD_myRequest = WDVarNonAllouee.ref;
WDObjet vWD_url = new WDChaineA();

WDObjet vWD_MyResponse = WDVarNonAllouee.ref;



try
{
// myRequest	is httpRequest
vWD_myRequest = new WDInstance( new WDHTTPRequete() );


// url			is  string	= "http://localhost:8080/user/getUser"

vWD_url.setValeur("http://localhost:8080/user/getUser");


// myRequest.URL	= url
vWD_myRequest.setProp(EWDPropriete.PROP_URL,vWD_url);

// myRequest..Timeout = 280s
vWD_myRequest.setProp(EWDPropriete.PROP_DUREENONREPONSE,(new WDDuree("0000440000")));

// myRequest.Method= httpGet
vWD_myRequest.setProp(EWDPropriete.PROP_METHODE,1);

// MyResponse is httpResponse = HTTPSend(myRequest)
vWD_MyResponse = new WDInstance( new WDHTTPReponse() );

vWD_MyResponse.setValeur(WDAPIHttp.HTTPEnvoie(vWD_myRequest));


// IF MyResponse..StatusCode = 200 THEN
if(vWD_MyResponse.getProp(EWDPropriete.PROP_CODEETAT).opEgal(200, 0))
{
// 	Info(MyResponse..Content)
WDAPIDialogue.info(vWD_MyResponse.getProp(EWDPropriete.PROP_CONTENU).getString());

}
else
{
// 	Info("ERRO")
WDAPIDialogue.info("ERRO");

}

}
catch(WDErreurNonFatale | WDException eCatch)
{
eCatch.catch_GEN();
return;
}
}
finally
{
finExecProcGlobale();

}
}



////////////////////////////////////////////////////////////////////////////
// Déclaration des variables globales
////////////////////////////////////////////////////////////////////////////
}
