$(function() {
	for (i = 0, l = summarizerData.features.length; i < l; i++) {
		var feature = summarizerData.features[i];
		var collapseHead = '';
		var collapseFoot = '';
		var collapseBody = '';
		var collapse = '';

		collapseHead += '<div class="panel panel-default">';
		collapseHead += '<div class="panel-heading">';
		collapseHead += '<h4 class="panel-title">';
		collapseHead += '<a data-toggle="collapse" data-parent="#accordion" href="#collapse'
				+ i + '">' + feature.nom + '</a>';
		collapseHead += '</h4>';
		collapseHead += '</div>';
		collapseHead += '<div id="collapse' + i
				+ '" class="panel-collapse collapse">';
		collapseHead += '<div id="collapse' + i
				+ '" class="panel-collapse collapse in">';
		collapseHead += '<div class="panel-body">';
		collapseFoot += '</div></div></div></div>';

		var types = feature.types;
		for (j = 0; j < types.length; j++) {
			var type = types[j].type;
			var resume = types[j].resume;
			var pourcentageType = types[j].pourcentageType;
			
			switch (type) {
			case "numbers":
				collapseBody += numbersResume(resume);
				break;
			case "booleans":
				collapseBody += booleansResume(resume);
				break;
			case "notAvailables":
				collapseBody += notAvailablesResume(pourcentageType);
				break;
			/*case "stringValues":
				collapseBody += stringValuesResume(resume);
				break;*/
			default:
				collapseBody += stringValuesResume(resume);
				break;
			}
		}

		collapse = collapseHead + collapseBody + collapseFoot;
		$("#accordion").append(collapse);
	}
});

/**
 * 
 * @param resume
 */
function numbersResume(resumeData){
	// afficher la moyenne
	var moyenne = resumeData.moyenne;
	var max = resumeData.max;
	var min = resumeData.min;
	var ecart = resumeData.ecartType;

	var resume = '<div>La moyenne des valeurs est de <span class="data">'
			+ moyenne.toFixed(2)
			+ '</span> avec un écart type de <span class="data">'
			+ ecart.toFixed(2) 
			+ '</span>, un maximum de <span class="data">'
			+ max 
			+'</span> et un minimum de <span class="data">'
			+ min + '</span></div>'
			
	return resume;
}

/**
 * 
 * @param resume
 */
function booleansResume(resumeData){
	var pourcentage = resumeData.pourcentage;
	var array = Object.keys(pourcentage);
	var resume = '<div>Le feature présente les valeurs suivantes: ';
	
	for (cpt = 0; cpt < array.length; cpt++) {
		var key = array[cpt];
		resume += '<div><span class="data">' + key
				+ ':</span> ' + pourcentage[key].toFixed(2) + '%</div>';
	}
	resume += '</div>';
	
	return resume;
}

/**
 * 
 * @param resume
 */
function notAvailablesResume(pourcentageType){
	var resume = '<div> <span class="data">' + pourcentageType.toFixed(2)
			+ ' %</span> des valeurs ne sont pas renseignées</div>';
	
	return resume;
}

/**
 * 
 * @param resume
 */
function stringValuesResume(resumeData){
	var resume = '';
	
	var max = resumeData.max;
	if(typeof max !== "undefined"){
		var array_max = Object.keys(max);
		if(array_max.length > 1){
			resume += '<div>Les valeurs les plus representées avec un pourcentage de <span class="data">'
				+ max[array_max[0]].toFixed(2) + '%</span> sont: </br>';
			for (cpt = 0; cpt < array_max.length; cpt++) {
				var key = array_max[cpt];
				resume += key + ', ';
			}
		}
		else{
			resume += '<div>La valeur la plus representée avec un pourcentage de <span class="data">'
				+ max[array_max[0]].toFixed(2) + '%</span> est: </br>';
			resume += array_max[0];
		}
		
		resume += '</div>';
	}

	var min = resumeData.min;
	if(typeof min !== "undefined"){
		var array_min = Object.keys(min);
		if(array_min.length > 1){
			resume += '<div>Les valeurs les moins representées avec un pourcentage de <span class="data">'
				+ min[array_min[0]].toFixed(2) + '%</span> sont: </br>';
			for (cpt = 0; cpt < array_min.length; cpt++) {
				var key = array_min[cpt];
				resume += key + ', ';
			}			
		}
		else{
			resume += '<div>La valeur la moins representée avec un pourcentage de <span class="data">'
				+ min[array_min[0]].toFixed(2) + '%</span> est: </br>';
			resume += array_min[0];
		}
		
		resume += '</div>';
	}
	
	if(resume.length == 0){
		resume += 'Les valeurs sont toutes distinctes.';
	}
	
	return resume;
}