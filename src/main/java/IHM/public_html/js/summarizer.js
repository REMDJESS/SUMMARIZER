
            $(function () {

                $.getJSON('json/summarizer.json', function (data) {

                    for (i = 0; i < data.features.length; i++) {
                        var feature = data.features[i];
                        var collapseHead = '';
                        var collapseFoot = '';
                        var collapseBody = '';
                        var collapse = '';

                        collapseHead += '<div class="panel panel-default">';
                        collapseHead += '<div class="panel-heading">';
                        collapseHead += '<h4 class="panel-title">';
                        collapseHead += '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + i + '">' + feature.nom + '</a>';
                        collapseHead += '</h4>';
                        collapseHead += '</div>';
                        collapseHead += '<div id="collapse' + i + '" class="panel-collapse collapse">';
                        collapseHead += '<div id="collapse' + i + '" class="panel-collapse collapse in">';
                        collapseHead += '<div class="panel-body">';
                        collapseFoot += '</div></div></div></div>';

                        var types = feature.types;
                        for (j = 0; j < types.length; j++) {
                            var type = types[j].type;
                            var resume = types[j].resume;
                            switch (type) {
                                case "numbers":
                                    //afficher la moyenne
                                    var moyenne = resume.moyenne;
                                    var max = resume.max;
                                    var min = resume.min;
                                    var ecart = resume.ecartType;

                                    collapseBody += '<div>La moyenne des valeurs est de <span class="data">' + moyenne + '</span> \n\
                                                        avec un écart type de <span class="data">' + ecart + '</span> </div>'

                                    break;
                                case "booleans":
                                    var pourcentage = resume.pourcentage;
                                    var array = Object.keys(pourcentage);
                                    collapseBody += '<div>Le feature présente les valeurs suivantes: ';
                                    for (cpt = 0; cpt < array.length; cpt++) {
                                        var key = array[cpt];
                                        collapseBody += '<div><span class="data">' + key + ':</span> ' + pourcentage[key] + '</div>';
                                    }
                                    collapseBody += '</div>';
                                    break;
                                case "notAvailables":
                                    var pourcentage = types.porcentageType;
                                    collapseBody += '<div> ' + types[j].porcentageType+ ' % des valeurs ne sont pas renseinées</div>';
                                    break;
                                case "stringValues":                                  
                                    var max = resume.max;
                                    var array_max = Object.keys(max);
                                    collapseBody += '<div>Les valeurs les plus representées avec un pourcentage de <span class="data">' + max[array_max[0]] + '%</span> sont: ';
                                    for (cpt = 0; cpt < array_max.length; cpt++) {
                                        var key = array_max[cpt];
                                        collapseBody += key + ', ';
                                    }
                                    collapseBody += '</div>';

                                    var min = resume.min;
                                    var array_min = Object.keys(min);
                                    collapseBody += '<div>Les valeurs les moins representées avec un pourcentage de <span class="data">' + min[array_min[0]] + '%</span> sont: ';
                                    for (cpt = 0; cpt < array_min.length; cpt++) {
                                        var key = array_min[cpt];
                                        collapseBody += key + ', ';
                                    }
                                    collapseBody += '</div>';
                                    break;
                                case "multiples":                                  
                                    var max = resume.max;
                                    var array_max = Object.keys(max);
                                    collapseBody += '<div>Les valeurs les plus representées avec un pourcentage de <span class="data">' + max[array_max[0]] + '%</span> sont: ';
                                    for (cpt = 0; cpt < array_max.length; cpt++) {
                                        var key = array_max[cpt];
                                        collapseBody += key + ', ';
                                    }
                                    collapseBody += '</div>';

                                    var min = resume.min;
                                    var array_min = Object.keys(min);
                                    collapseBody += '<div>Les valeurs les moins representées avec un pourcentage de <span class="data">' + min[array_min[0]] + '%</span> sont: ';
                                    for (cpt = 0; cpt < array_min.length; cpt++) {
                                        var key = array_min[cpt];
                                        collapseBody += key + ', ';
                                    }
                                    collapseBody += '</div>';
                                    break;
                                default:

//                                    var max = resume.max;
//                                    var array_max = Object.keys(max);
//                                    collapseBody += '<div>Les valeurs les plus representées avec un pourcentage de <span class="data">' + max[array_max[0]] + '%</span> sont: ';
//                                    for (cpt = 0; cpt < array_max.length; cpt++) {
//                                        var key = array_max[cpt];
//                                        collapseBody += key + ', ';
//                                    }
//                                    collapseBody += '</div>';
//
//                                    var min = resume.min;
//                                    var array_min = Object.keys(min);
//                                    collapseBody += '<div>Les valeurs les moins representées avec un pourcentage de <span class="data">' + min[array_min[0]] + '%</span> sont: ';
//                                    for (cpt = 0; cpt < array_min.length; cpt++) {
//                                        var key = array_min[cpt];
//                                        collapseBody += key + ', ';
//                                    }
//                                    collapseBody += '</div>';
                                    break;
                            }
                        }

                        collapse = collapseHead + collapseBody + collapseFoot;
                        $("#accordion").append(collapse);
                    }

                    // $.each(data.features,function(index,d){
                    /*for (i = 0; i < data.features.length; i++) {
                     var feature = data.features[i];
                     //                        $('.panel-title').append('<a data-toggle="collapse" data-parent="#accordion" href="'+feature.nom+'">feature.nom</a>');
                     console.log("FONCTIONNALITE : " + feature.nom);
                     $('#zone').append('<p>  Nom : ' + feature.nom + '</p>');
                     var types = feature.types;
                     for (j = 0; j < types.length; j++) {
                     console.log(" valeur: " + types[j].type + " pourcentage: " + types[j].porcentageType);
                     $('#zone').append(" valeur: " + types[j].type + " pourcentage: " + types[j].porcentageType);
                     $('#zone').append("<br>")
                     // si type= String, Multiple,Conditional
                     
                     if ((types[j].type === "String") || (types[j].type === "Multiple") || (types[j].type === "Conditional")) {
                     //                             afficher les valeurs du minimum
                     var max = types[j].resume.max;
                     //                                console.log(Object.keys(max));
                     var array = Object.keys(max);
                     $('#zone').append("valeur maximale:");
                     for (cpt = 0; cpt < array.length; cpt++) {
                     var key = array[cpt];
                     console.log("++++valeur maximale+++");
                     
                     console.log("nom: " + key + "; % " + max[key]);
                     $('#zone').append("nom: " + key + "; % " + max[key]);
                     
                     }
                     //afficher les valeurs du minimum
                     var min = types[j].resume.min;
                     //                                console.log(Object.keys(max));
                     var array = Object.keys(min);
                     for (cpt = 0; cpt < array.length; cpt++) {
                     var key = array[cpt];
                     console.log("++++valeur minimum+++");
                     console.log("nom: " + key + "% " + min[key]);
                     }
                     
                     }
                     
                     // si type=Number 
                     else if (types[j].type === "Number") {
                     //afficher la moyenne
                     var moyenne = types[j].resume.moyenne;
                     console.log("moyenne:" + ecart);
                     //afficher le maximum
                     var max = types[j].resume.max;
                     console.log("maximum :" + max);
                     //afficher le minimum
                     var min = types[j].resume.min;
                     console.log("minimum :" + min);
                     
                     //afficher l'ecart-type
                     var ecart = types[j].resume.ecartType;
                     console.log("ecart-type :" + ecart);
                     } else if (types[j].type === "Boolean") {
                     var pourcentage = types[j].resume.pourcentage;
                     var array = Object.keys(pourcentage);
                     for (cpt = 0; cpt < array.length; cpt++) {
                     var key = array[cpt];
                     console.log("nom: " + key + "--> " + pourcentage[key]);
                     }
                     } else if (types[j].type === "Boolean") {
                     
                     }
                     console.log("--------------------------------------------------------------------");
                     
                     }
                     
                     $('#zone').append('<hr>');
                     }*/

                });


            });
        