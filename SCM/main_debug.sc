DEFINE MISSIONS 9
DEFINE MISSION 0 AT @M00_INTRO          		// Formerly INTRO
DEFINE MISSION 1 AT @M01_GIVEMELIBERTY			// Formerly EIGHT
DEFINE MISSION 2 AT @M02_DONTSPANKMABITCHUP		// Formerly LUIGI2
DEFINE MISSION 3 AT @M03_DRIVEMISTYFORME		// Formerly LUIGI3
DEFINE MISSION 4 AT @M04_MIKELIPSLASTLUNCH		// Formerly JOEY1
DEFINE MISSION 5 AT @M05_FAREWELLCHUNKYLEECHONG		// Formerly JOEY2
DEFINE MISSION 6 AT @M06_VANHEIST			// Formerly JOEY3
DEFINE MISSION 7 AT @M07_CIPRIANISCHAUFFEUR		// Formerly JOEY4
DEFINE MISSION 8 AT @M08_TAKINGOUTTHELAUNDRY		// Formerly TONI1
//DEFINE MISSION 9 AT @M09_THEPICKUP			// Formerly TONI2
//DEFINE MISSION 10 AT @M10_SALVATORESCALLEDAMEETING	// Formerly TONI3
//DEFINE MISSION 11 AT @M11_TRIADSANDTRIBULATIONS		// Formerly TONI4
//DEFINE MISSION 12 AT @M12_BLOWFISH			// Formerly TONI5
//DEFINE MISSION 13 AT @M13_CHAPERONE			// Formerly FRANK1
//DEFINE MISSION 14 AT @M14_CUTTINGTHEGRASS		// Formerly FRANK2
//DEFINE MISSION 15 AT @M15_BOMBDABASEACTI		// Formerly FRANK21
//DEFINE MISSION 16 AT @M16_BOMBDABASEACTII		// Formerly FRANK3
//DEFINE MISSION 17 AT @M17_LASTREQUESTS			// Formerly FRANK4
//DEFINE MISSION 18 AT @M18_SAYONARASALVATORE		// Formerly ASUKA1
//DEFINE MISSION 19 AT @M19_UNDERSURVEILLANCE		// Formerly ASUKA2
//DEFINE MISSION 20 AT @M20_PAPARAZZIPURGE		// Formerly ASUKA3
//DEFINE MISSION 21 AT @M21_PAYDAYFORRAY			// Formerly ASUKA4
//DEFINE MISSION 22 AT @M22_SILENCETHESNEAK		// Formerly RAY1
//DEFINE MISSION 23 AT @M23_ARMSSHORTAGE			// Formerly RAY2
//DEFINE MISSION 24 AT @M24_EVIDENCEDASH			// Formerly RAY3
//DEFINE MISSION 25 AT @M25_LIBERATOR			// Formerly LOVE1
//DEFINE MISSION 26 AT @M26_WAKAGASHIRAWIPEOUT		// Formerly LOVE2
//DEFINE MISSION 27 AT @M27_ADROPINTHEOCEAN		// Formerly LOVE3
//DEFINE MISSION 28 AT @M28_GRANDTHEFTAERO		// Formerly LOVE4
//DEFINE MISSION 29 AT @M29_BAIT				// Formerly ASUSB1
//DEFINE MISSION 30 AT @M30_ESPRESSO2GO			// Formerly ASUSB2
//DEFINE MISSION 31 AT @M31_SAM				// Formerly ASUSB3
//DEFINE MISSION 32 AT @M32_THEEXCHANGE			// Formerly CAT11

{$INCLUDE constants.sc}

//-------------MAIN---------------
03A4: name_thread 'MAIN' 
0053: $PLAYER_CHAR = create_player #NULL at 811.875 -939.9375 35.75
0171: set_player $PLAYER_CHAR z_angle_to 180.0 
01F5: $PLAYER_ACTOR = create_emulated_actor_from_player $PLAYER_CHAR 
gosub @DEBUG_VARIABLES
0004: $MASTERDEBUG                = 1 // OVERRIDE VALUE SET IN debug.sc FOR COMPILING CONVENIENCE
042C: set_total_missions_to 33
030D: set_total_mission_points_to 999 
01F0: set_max_wanted_level_to 6

gosub @VARIABLE_INIT
gosub @SETUP_DYNAMIC_OBJECTS
gosub @INIT_SPECIAL_OBJECTS
gosub @GARAGES_INIT
gosub @TRAFFIC_INFO
gosub @CAR_GENERATORS
gosub @MAIN_PICKUPS
gosub @HIDDEN_PACKAGES

gosub @INIT_SOUND_LOOPS

0111: set_wasted_busted_check_to 1

gosub @INIT_MISSION_LOCATIONS
gosub @INIT_THREADS


// Targetable objects require player to be defined!
035D: make_object $FAKETARGET1 targetable 
035D: make_object $FAKETARGET2 targetable 
035D: make_object $FAKETARGET3 targetable 
if 
	0256:   is_player $PLAYER_CHAR defined 
then
	01B4: set_player $PLAYER_CHAR controllable 0
end
0169: set_fade_color 0 0 0 
016A: fade 0 for 0 ms 
0417: start_mission M00_INTRO
004F: create_thread @PORTLAND_SAVE
004F: create_thread @STAUNTON_SAVE
004F: create_thread @SSV_SAVE
004F: create_thread @PORTLAND_RESTART
004F: create_thread @STAUNTON_RESTART
004F: create_thread @SSV_RESTART
if
	0256:   is_player $PLAYER_CHAR defined 
then
	01B4: set_player $PLAYER_CHAR controllable 1
end
while true
	wait 1000 ms
end // main loop that constantly runs

//Main threads
{$INCLUDE camera.sc}   	//CAMERA
{$INCLUDE car_gen.sc}  	//CAR_GENERATORS
{$INCLUDE debug.sc}    	//DEBUG_VARIABLES - STARTER - COORDS
{$INCLUDE gates.sc}    	//FISH_FACTORY_GATE - DOG_FOOD_FACTORY_GATE - POLICE_GATE1 - POLICE_GATE2 - COLOMBIAN_GATE - PHILS_GATE - COLOMBIAN_GATE2
{$INCLUDE genstuf.sc}  	//GENSTUFF - COBBLERS - IND_AMMU - FISH_FACTORY_GEN - TRAMP_TUNNEL - IND_SOUND - DOG_SOUND - COM_AMMU - COM_CAR_PARK
{$INCLUDE help.sc}	//PISTOL_MESSAGE - UZI_MESSAGE - JOEYS_BUGGY_LOOP - TONI5_FLAMES_LOOP - TONI4_PAGER_LOOP - TONI5_PAGER_LOOP - CLOSE_ASUKA1_DOOR
{$INCLUDE hj.sc}	//INSANE_STUNT
{$INCLUDE init.sc}	//SETUP_DYNAMIC_OBJECTS - INIT_SPECIAL_OBJECTS - GARAGES_INIT - TRAFFIC_INFO - VARIABLE_INIT - INIT_MISSION_LOCATIONS - INIT_THREADS - INIT_SOUND_LOOPS
{$INCLUDE packages.sc}	//HIDDEN_PACKAGES
{$INCLUDE pickups.sc}  	//MAIN_PICKUPS
{$INCLUDE rampage.sc}  	//KILL_FRENZY
{$INCLUDE rewards.sc}  	//ALL_REWARDS
{$INCLUDE save.sc}     	//PORTLAND_SAVE - STAUNTON_SAVE - SSV_SAVE - PORTLAND_RESTART - STAUNTON_RESTART - SSV_RESTART
{$INCLUDE triggers.sc} 	//All mission triggers
{$INCLUDE usj.sc}      	//USJ




//Missions
{$INCLUDE 00_intro.sc} // M00_INTRO
{$INCLUDE 01_8ball.sc} // M01_GIVEMELIBERTY
{$INCLUDE 02_luigi2.sc} // M02_DONTSPANKMABITCHUP
{$INCLUDE 03_luigi3.sc} // M03_DRIVEMISTYFORME
{$INCLUDE 04_joey1.sc} // M04_MIKELIPSLASTLUNCH
{$INCLUDE 05_joey2.sc} // M05_FAREWELLCHUNKYLEECHONG
{$INCLUDE 06_joey3.sc} // M06_VANHEIST
{$INCLUDE 07_joey4.sc} // M07_CIPRIANISCHAUFFEUR
{$INCLUDE 08_toni1.sc} // M08_TAKINGOUTTHELAUNDRY
//{$INCLUDE 09_toni2.sc} // M09_THEPICKUP
//{$INCLUDE 10_toni3.sc} // M10_SALVATORESCALLEDAMEETING
//{$INCLUDE 11_toni4.sc} // M11_TRIADSANDTRIBULATIONS
//{$INCLUDE 12_toni5.sc} // M12_BLOWFISH
//{$INCLUDE 13_frank1.sc} // M13_CHAPERONE
//{$INCLUDE 14_frank2.sc} // M14_CUTTINGTHEGRASS
//{$INCLUDE 15_frank2.1.sc} // M15_BOMBDABASEACTI
//{$INCLUDE 16_frank3.sc} // M16_BOMBDABASEACTII
//{$INCLUDE 17_frank4.sc} // M17_LASTREQUESTS
//{$INCLUDE 18_asuka1.sc} // M18_SAYONARASALVATORE
//{$INCLUDE 19_asuka2.sc} // M19_UNDERSURVEILLANCE
//{$INCLUDE 20_asuka3.sc} // M20_PAPARAZZIPURGE
//{$INCLUDE 21_asuka4.sc} // M21_PAYDAYFORRAY
//{$INCLUDE 22_ray1.sc} // M22_SILENCETHESNEAK
//{$INCLUDE 23_ray2.sc} // M23_ARMSSHORTAGE
//{$INCLUDE 24_ray3.sc} // M24_EVIDENCEDASH
