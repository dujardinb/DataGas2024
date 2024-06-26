package fr.rvd.dsi.datagas2024.BduLib;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 07/03/2015.
 */
public class Prefs {


    public static final String PARAM_PREF = "ISDND";
    public static final String CONST_IMEI = "IMEI";
    public static final String CONST_DISPLAYNAME = "DISPLAYEMAIL";
    public static final String CONST_NUM_SITES = "sites";
    public static final String CONST_CODE_SITE = "CodeSite";
    public static final String CONST_SHEET_ADRESSE = "SheetAdresse";
    public static final String CONST_ACCOUNT_NAME = "accountName";
    public static final String CONST_ACCOUNT_PHOTO_URL = "accountPhotoUrl";

    public static final String CONST_CODE_CASIER = "codecasier";
    public static final String CONST_ID_CASIER = "idcasier";

    public static final String CONST_CODE_COLLECTEUR = "codecollecteur";
    public static final String CONST_ID_COLLECTEUR = "idcollecteur";

    public static final String CONST_CODE_PUITS = "codepuit";
    public static final String CONST_ID_PUITS = "idpuit";

    public static final String CONST_CODE_DRAIN = "codedrain";
    public static final String CONST_ID_DRAIN = "iddrain";

    public static final String CONST_CODE_POINTMESURE = "codepointmesure";
    public static final String CONST_ID_POINTMESURE = "idpointmesure";

    public static final String CONST_DATE_SAISIE = "dateSaisie";
    public static final String CONST_HEURE_SAISIE = "heureSaisie";

    public static final String CONST_PRESSION = "pression";
    public static final String CONST_TEMPERATURE = "temperature";

    public static final String CONST_LV_CASIERS_INDEX = "lvCasierIndex";
    public static final String CONST_LV_COLLECTEURS_INDEX = "lvCollecteursIndex";

    public static final String CONST_LV_PUITS_INDEX = "lvPuitsIndex";
    public static final String CONST_LV_DRAINS_INDEX = "lvDrainsIndex";

    public static final String CONST_LV_POINTSMESURE_INDEX = "lvPointsMesureIndex";
    public static final String CONST_CTRL_DEBRAYABLE = "ctrlDebrayable";



    public static void setCtrlDebrayable(Context ct, int index) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_CTRL_DEBRAYABLE)) editor.remove(CONST_CTRL_DEBRAYABLE);
        editor.putInt(CONST_CTRL_DEBRAYABLE, index);
        editor.commit();
    }

    public static int getCtrlDebrayable(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getInt(CONST_CTRL_DEBRAYABLE,0);
    }



    /*********************************************************************
     * Sauvegarde de l'index sélectionné dans la liste des Points Mesure
     * @Context ct
     * @int index
     */
    public static void setLvPointsMesureIndex(Context ct, int index){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_LV_POINTSMESURE_INDEX)) editor.remove(CONST_LV_POINTSMESURE_INDEX);
        editor.putInt(CONST_LV_POINTSMESURE_INDEX, index);
        editor.commit();
    }

    /****************************************************************************
     * Retourne l'index sauvegardé de la liste ces Drains
     * @Context ct
     * @return int index ou -1 si aucun index sauvegardé
     */
    public static int getLvPointsMesureIndex(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getInt(CONST_LV_POINTSMESURE_INDEX,-1);
    }

    /*********************************************************************
     * Sauvegarde de l'index sélectionné dans la liste des Drains
     * @Context ct
     * @int index
     */
    public static void setLvDrainsIndex(Context ct, int index){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_LV_DRAINS_INDEX)) editor.remove(CONST_LV_DRAINS_INDEX);
        editor.putInt(CONST_LV_DRAINS_INDEX, index);
        editor.commit();
    }

    /****************************************************************************
     * Retourne l'index sauvegardé de la liste ces Drains
     * @Context ct
     * @return int index ou -1 si aucun index sauvegardé
     */
    public static int getLvDrainsIndex(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getInt(CONST_LV_DRAINS_INDEX,-1);
    }

    /*********************************************************************
     * Sauvegarde de l'index sélectionné dans la liste des Puits
     * @Context ct
     * @int index
     */
    public static void setLvPuitsIndex(Context ct, int index){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_LV_PUITS_INDEX)) editor.remove(CONST_LV_PUITS_INDEX);
        editor.putInt(CONST_LV_PUITS_INDEX, index);
        editor.commit();
    }

    /****************************************************************************
     * Retourne l'index sauvegardé de la liste ces puits
     * @Context ct
     * @return int index ou -1 si aucun index sauvegardé
     */
    public static int getLvPuitsIndex(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getInt(CONST_LV_PUITS_INDEX,-1);
    }


    /*********************************************************************
     * Sauvegarde de l'index sélectionné dans la liste des casiers
     * @Context ct
     * @int index
     */
    public static void setLvCasiersIndex(Context ct, int index){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_LV_CASIERS_INDEX)) editor.remove(CONST_LV_CASIERS_INDEX);
        editor.putInt(CONST_LV_CASIERS_INDEX, index);
        editor.commit();
    }

    /****************************************************************************
     * Retourne l'index sauvegardé de la liste ces casiers
     * @Context ct
     * @return int index ou -1 si aucun index sauvegardé
     */
    public static int getLvCasiersIndex(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getInt(CONST_LV_CASIERS_INDEX,-1);
    }


    /*********************************************************************
     * Sauvegarde de l'index sélectionné dans la liste des collecteurs
     * @Context ct
     * @int index
     */
    public static void setConstLvCollecteursIndex(Context ct, int index){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_LV_COLLECTEURS_INDEX)) editor.remove(CONST_LV_COLLECTEURS_INDEX);
        editor.putInt(CONST_LV_COLLECTEURS_INDEX, index);
        editor.commit();
    }

    /****************************************************************************
     * Retourne l'index sauvegardé de la liste ces collecteurs
     * @Context ct
     * @return int index ou -1 si aucun index sauvegardé
     */
    public static int getLvCollecteursIndex(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getInt(CONST_LV_COLLECTEURS_INDEX,-1);
    }

    /******************************************************
     * Enregistre le N° d'IMEI
     * @Context ct
     * @Valeur de l'IMEI value
     *********************************************************/
    public static void SetIEMI(Context ct, String value){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_IMEI)) editor.remove(CONST_IMEI);
        editor.putString(CONST_IMEI, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le N° d'IMEI
     * @Context ct
     * @return String N° d'IMEI
     *********************************************************/
    public static String GetIMEI(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_IMEI, "");
    }

    /******************************************************
     * Enregistre le nom du propriétaire du compte
     * @Context ct
     * @String de l'email value
     *********************************************************/
    public static void setDisplayName(Context ct, String value){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_DISPLAYNAME)) editor.remove(CONST_DISPLAYNAME);
        editor.putString(CONST_DISPLAYNAME, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le nom du propriétaire du compte
     * @Context ct
     * @return String nom
     *********************************************************/
    public static String getDisplayName(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_DISPLAYNAME, "");
    }

    /*************************************************************
     * Retourne compte email qui va être utilisé
     * @Context ct
     * @String value
     */
    public static void setPrefAccountName(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_ACCOUNT_NAME)) editor.remove(CONST_ACCOUNT_NAME);
        editor.putString(CONST_ACCOUNT_NAME, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le compte email si il existe
     * @Context ct
     * @return String email
     *********************************************************/
    public static String getPrefAccountName(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_ACCOUNT_NAME, null);
    }
    /*************************************************************
     * Stock  l'Url de la photo du compte
     * @Context ct
     * @String value
     */
    public static void setPrefAccountPhotoUrl(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_ACCOUNT_PHOTO_URL)) editor.remove(CONST_ACCOUNT_PHOTO_URL);
        editor.putString(CONST_ACCOUNT_PHOTO_URL, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le compte si il existe
     * @Context ct
     * @return String email
     *********************************************************/
    public static String getPrefAccountPhotoUrl(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_ACCOUNT_PHOTO_URL, null);
    }


    /*************************************************************
     * Sauvegarde le code du site choisi
     * @Context ct
     * @Long value
     */
    public static void setPrefDataSiteCode(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_CODE_SITE)) editor.remove(CONST_CODE_SITE);
        editor.putString(CONST_CODE_SITE, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code de Datasite sélectionné
     * @Context ct
     * @Long    ID
     *********************************************************/
    public static String getPrefDataSiteCode(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_CODE_SITE, null);
    }

    /*************************************************************
     * Sauvegarde l'adresse de la feuille GoogleSheet
     * @Context ct
     * @String    Adresse de la sheet
     */
    public static void setPrefDataSiteSheetAdresse(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_SHEET_ADRESSE)) editor.remove(CONST_SHEET_ADRESSE);
        editor.putString(CONST_SHEET_ADRESSE, value);
        editor.commit();
    }

    /******************************************************
     * Retourne l'adresse de la feuille GoogleSheet pour le site en cours
     * @Context ct
     * @String    Adresse de la sheet
     *********************************************************/
    public static String getPrefDataSiteSheetAdresse(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_SHEET_ADRESSE, null);
    }

    /*************************************************************
     * Sauvegarde du Id casier sélectionné
     * @Context ct
     * @Long    Id casier
     */
    public static void setPrefDataIdCasier(Context ct, Long value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_ID_CASIER)) editor.remove(CONST_ID_CASIER);
        editor.putLong(CONST_ID_CASIER, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le Id casier sellectionné
     * @Context ct
     * @Long    Id casier
     *********************************************************/
    public static Long getPrefDataIdCasier(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getLong(CONST_ID_CASIER, -1);
    }

    /*************************************************************
     * Sauvegarde du code casier sélectionné
     * @Context ct
     * @String    code casier
     */
    public static void setPrefDataCodeCasier(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_CODE_CASIER)) editor.remove(CONST_CODE_CASIER);
        editor.putString(CONST_CODE_CASIER, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code casier sellectionné
     * @Context ct
     * @Long    Id casier
     *********************************************************/
    public static String getPrefDataCodeCasier(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_CODE_CASIER, "");
    }
    /*************************************************************
     * Sauvegarde du code collecteur  sélectionné
     * @Context ct
     * @Long    Id Collecteur
     */
    public static void setPrefDataIdCollecteur(Context ct, Long value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_ID_COLLECTEUR)) editor.remove(CONST_ID_COLLECTEUR);
        editor.putLong(CONST_ID_COLLECTEUR, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le ID collecteur selectionné
     * @Context ct
     * @String    Id collecteur
     *********************************************************/
    public static Long getPrefDataIdCollecteur(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getLong(CONST_ID_COLLECTEUR, -1);
    }


    /*************************************************************
     * Sauvegarde du code collecteur  sélectionné
     * @Context ct
     * @String    code Collecteur
     */
    public static void setPrefDataCodeCollecteur(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_CODE_COLLECTEUR)) editor.remove(CONST_CODE_COLLECTEUR);
        editor.putString(CONST_CODE_COLLECTEUR, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code collecteur selectionné
     * @Context ct
     * @String    code collecteur
     *********************************************************/
    public static String getPrefDataCodeCollecteur(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_CODE_COLLECTEUR, "");
    }




    /*************************************************************
     * Sauvegarde du ID puit  sélectionné
     * @Context ct
     * @Long    id puit
     */
    public static void setPrefDataIdPuit(Context ct, Long value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_ID_PUITS)) editor.remove(CONST_ID_PUITS);
        editor.putLong(CONST_ID_PUITS, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code collecteur selectionné
     * @Context ct
     * @Long    id  puit ou -1
     *********************************************************/
    public static Long getPrefDataIdPuit(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getLong(CONST_ID_PUITS, -1);
    }



    /*************************************************************
     * Sauvegarde du code puit  sélectionné
     * @Context ct
     * @String    id puit
     */
    public static void setPrefDataCodePuit(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_CODE_PUITS)) editor.remove(CONST_CODE_PUITS);
        editor.putString(CONST_CODE_PUITS, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code collecteur selectionné
     * @Context ct
     * @STRING    code  puit ou ""
     *********************************************************/
    public static String getPrefDataCodePuit(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_CODE_PUITS, "");
    }

    /*************************************************************
     * Sauvegarde du code drain  sélectionné
     * @Context ct
     * @Long    id drain
     */
    public static void setPrefDataIdDrain(Context ct, Long value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_ID_DRAIN)) editor.remove(CONST_ID_DRAIN);
        editor.putLong(CONST_ID_DRAIN, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code drain selectionné
     * @Context ct
     * @Long    id puit ou -1
     *********************************************************/
    public static Long getPrefDataIdDrain(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getLong(CONST_ID_DRAIN, -1);
    }

    /*************************************************************
     * Sauvegarde du code drain  sélectionné
     * @Context ct
     * @String    code drain
     */
    public static void setPrefDataCodeDrain(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_CODE_DRAIN)) editor.remove(CONST_CODE_DRAIN);
        editor.putString(CONST_CODE_DRAIN, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code drain selectionné
     * @Context ct
     * @String    code drain ou éé
     *********************************************************/
    public static String getPrefDataCodeDrain(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_CODE_DRAIN, "");
    }
    /*************************************************************
     * Sauvegarde du code point mesure  sélectionné
     * @Context ct
     * @Long    Id point mesure
     */
    public static void setPrefDataIdPointMesure(Context ct, Long value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_ID_POINTMESURE)) editor.remove(CONST_ID_POINTMESURE);
        editor.putLong(CONST_ID_POINTMESURE, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code point mesure selectionné
     * @Context ct
     * @String    code point mesure
     *********************************************************/
    public static Long getPrefDataIdPointMesure(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getLong(CONST_ID_POINTMESURE, -1);
    }




    /*************************************************************
     * Sauvegarde du code point mesure  sélectionné
     * @Context ct
     * @String    code point mesure
     */
    public static void setPrefDataCodePointMesure(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_CODE_POINTMESURE)) editor.remove(CONST_CODE_POINTMESURE);
        editor.putString(CONST_CODE_POINTMESURE, value);
        editor.commit();
    }

    /******************************************************
     * Retourne le code point mesure selectionné
     * @Context ct
     * @String    code point mesure
     *********************************************************/
    public static String getPrefDataCodePointMesure(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_CODE_POINTMESURE, "");
    }

    /***********************************************************
     * @Context ct
     * @String  value valeur de la pression
     */
    public static void setPression (Context ct, String value){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_PRESSION)) editor.remove(CONST_PRESSION);
        editor.putString(CONST_PRESSION, value);
        editor.commit();
    }

    /******************************************************
     * Retourne la valeur de la pression
     * @Context ct
     * @String    valeur de la pression
     *********************************************************/
    public static String getPression(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_PRESSION, "???");
    }

    /***********************************************************
     * @Context ct
     * @String  value valeur de la pression
     */
    public static void setTemperature (Context ct, String value){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_TEMPERATURE)) editor.remove(CONST_TEMPERATURE);
        editor.putString(CONST_TEMPERATURE, value);
        editor.commit();
    }

    /******************************************************
     * Retourne la valeur de la pression
     * @Context ct
     * @String    valeur de la pression
     *********************************************************/
    public static String getTemperature(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_TEMPERATURE, "???");
    }

    /*************************************************************
     * Sauvegarde la date de la saisie
     * @Context ct
     * @String    date de saisie
     */
    public static void setPrefDateSaisie(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_DATE_SAISIE)) editor.remove(CONST_DATE_SAISIE);
        editor.putString(CONST_DATE_SAISIE, value);
        editor.commit();
    }

    /******************************************************
     * Retourne la date de la saise
     * @Context ct
     * @String    date
     *********************************************************/
    public static String getPrefDateSaisie(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_DATE_SAISIE, null);
    }
    /*************************************************************
     * Sauvegarde la date de la saisie
     * @Context ct
     * @String    date de saisie
     */
    public static void setPrefHeureSaisie(Context ct, String value) {
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains(CONST_HEURE_SAISIE)) editor.remove(CONST_HEURE_SAISIE);
        editor.putString(CONST_HEURE_SAISIE, value);
        editor.commit();
    }

    /******************************************************
     * Retourne la date de la saise
     * @Context ct
     * @String    date
     *********************************************************/
    public static String getPrefHeureSaisie(Context ct){
        SharedPreferences pref = ct.getSharedPreferences(PARAM_PREF, Context.MODE_PRIVATE);
        return pref.getString(CONST_HEURE_SAISIE, null);
    }


}
