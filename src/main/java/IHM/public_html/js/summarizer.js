$(function () {
    for (i = 2, l = summarizerData.features.length; i < l; i++) {
        var choixFeature= summarizerData.features[0]
        var choixValeur= summarizerData.features[1]
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
        var choixFeature=choixFeature.featureChoisi; 
        var choixValeur=choixValeur.operateurChoisi; 
        console.log(summarizerData.features.length);
        console.log(choixFeature.featureChoisi);
        console.log(choixValeur.operateurChoisi);
        
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
    var data = "data"
     $("#choix").append(" </span> Feature selected: <span class="+data+"> "+choixFeature+" </span> Value selected: <span class="+data+"> "+choixValeur);
});
//builder.append("{ \"featureChoisi\": \" ").append(featureChoisi).append("\"},");
//        builder.append("{ \"operateurChoisi\": \" ").append(operateurChoisi).append("\"},");
/**
 * 
 * @param resume
 * contient le r�sum� � afficher pour le type numbersResume
 */
function numbersResume(resumeData) {
    // afficher la moyenne
    var moyenne = resumeData.moyenne;
    // valeur max
    var max = resumeData.max;
    //les produits du max
    var maxProduct = resumeData.productWithMax;
    //valeur minimale
    var min = resumeData.min;
    //les produits du min
    var minProduct = resumeData.productWithMin;
    //l'ecart-type
    var ecart = resumeData.ecartType;
// construction du r�sum�
    var resume = '<div>The value average is <span class="data">'
            + moyenne.toFixed(2) // on arrondit au centi�me pr�s
            + '</span> with a standard deviation of <span class="data">'
            + ecart.toFixed(2)
            + '</span>, a maximum value of <span class="data">'
            + max
            + '</span> with products <span class="data">'
            + maxProduct
            + '</span> and a minimum value of <span class="data">'
            + min 
            + '</span> with products <span class="data">'
            + minProduct + '</span></div>';

    return resume;
}

/**
 * 
 * @param resume
 */
function booleansResume(resumeData) {
    var pourcentage = resumeData.pourcentage;
    var array = Object.keys(pourcentage);
    var resume = '<div>The feature has the following values: ';

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
function notAvailablesResume(pourcentageType) {
    var resume = '<div> <span class="data">' + pourcentageType.toFixed(2)
            + ' %</span> of values are not given</div>';

    return resume;
}

/**
 * 
 * @param resume
 */
function stringValuesResume(resumeData) {
    var resume = '';

    var max = resumeData.max;
    if (typeof max !== "undefined") {
        var array_max = Object.keys(max);
        if (array_max.length > 1) {
            resume += '<div>The most represented values with a percentage of <span class="data">'
                    + max[array_max[0]].toFixed(2) + '%</span> are: </br>';
            for (cpt = 0; cpt < array_max.length; cpt++) {
                var key = array_max[cpt];
                resume += key + ', ';
            }
        } else {
            resume += '<div>The most represented value with a percentage of <span class="data">'
                    + max[array_max[0]].toFixed(2) + '%</span> is: </br>';
            resume += array_max[0];
        }

        resume += '</div>';
    }

    var min = resumeData.min;
    if (typeof min !== "undefined") {
        var array_min = Object.keys(min);
        if (array_min.length > 1) {
            resume += '<div>The least represented values with a percentage of <span class="data">'
                    + min[array_min[0]].toFixed(2) + '%</span> are: </br>';
            for (cpt = 0; cpt < array_min.length; cpt++) {
                var key = array_min[cpt];
                resume += key + ', ';
            }
        } else {
            resume += '<div>The least represented value with a percentage of <span class="data">'
                    + min[array_min[0]].toFixed(2) + '%</span> is: </br>';
            resume += array_min[0];
        }

        resume += '</div>';
    }

    if (resume.length == 0) {
        resume += 'The values are all different';
    }

    return resume;
}