



/************************************TEST DE TYPE STRING******************************************/

QUnit.test("TestString", function (assert) {
    // DONNEES de type String
    var resumeString = {"features": [{
                "nom": "Viewfinder magnification",
                "types": [{
                        "type": "String",
                        "pourcentageType": 100.0,
                        "resume": {
                            "pourcentage": {
                                "0.78x": 6.9767447,
                                "0.94x": 18.60465,
                                "0.86x": 9.302325,
                                "0.75x": 6.9767447,
                                "0.85x": 2.3255813,
                                "0.8x": 23.255816,
                                "0.7x": 27.906979,
                                "0.82x": 4.6511626
                            },
                            "max": {
                                "0.7x": 27.906979
                            },
                            "min": {
                                "0.85x": 2.3255813
                            }
                        }
                    }]
            }
        ]
    };

    // verifions le nom du feature
    var feature = resumeString.features[0];
    assert.equal(resumeString.features[0].nom, "Viewfinder magnification", "le nom feature est exact");
    //verifion le type
    var types = feature.types[0];
    var valeurType = types.type;
    assert.equal(valeurType, "String", "le feature est de type String");
    console.log(valeurType);
    //verifions la valeur du pourcentage
    var pourcentageType = types.pourcentageType;
    console.log(pourcentageType);
    assert.equal(pourcentageType, "100", "le pourcentage est exact");
    // verifions le contenu du resumé
    var resume = types.resume;
    //recuperons le max
    var max = resume.max;
    var array_max = Object.keys(max);
    var valeurMax = max[array_max[0]].toFixed(2);
    //la valeur du maximal
    assert.equal(valeurMax, "27.91", "le pourcentage max est exact");
    // le pourcentage du maximal
    var pourcentgaeMax = array_max[0];
    assert.equal(pourcentgaeMax, "0.7x", "la valeur du max est exact");

    /*******************************************************************************************/
    var min = resume.min;
    var array_min = Object.keys(min);
    var valeurMin = min[array_min[0]].toFixed(2);
    //la valeur du maximal
    assert.equal(valeurMin, "2.33", "le pourcentage min est exact");
    // le pourcentage du maximal
    var pourcentgaeMin = array_min[0];
    assert.equal(pourcentgaeMin, "0.85x", "la valeur du min est exact");


});





/**********************************************************************************/



QUnit.test("TestBoolean", function (assert) {
// test data
    var resumeBoolean = {"features": [{
                "nom": "Live view",
                "types": [{
                        "type": "booleans",
                        "pourcentageType": 100.0,
                        "resume": {
                            "pourcentage": {
                                "No": 39.534885,
                                "Yes": 60.465115}
                        }
                    }
                ]
            }
        ]
    };
    
   
    // verifions le nom du feature
    var feature = resumeBoolean.features[0];
    assert.equal(resumeBoolean.features[0].nom, "Live view", "le nom feature est exact");
    //verifion le type
    var types = feature.types[0];
    var valeurType = types.type;
    assert.equal(valeurType, "booleans", "le feature est de type boolean");
    console.log(valeurType);
    //verifions la valeur du pourcentage
    var pourcentageType = types.pourcentageType;
    console.log(pourcentageType);
    assert.equal(pourcentageType, "100", "le pourcentage est exact");
    // verifions le contenu du resumé
    var resume = types.resume;
    var pourcentage = resume.pourcentage;
    var array = Object.keys(pourcentage);
    // la premierse clé du tableau pourcentage
    var key_1= array[0];
    // le contenu de cette clé
    var value_key_1= pourcentage[key_1].toFixed(2);
    assert.equal(value_key_1, "39.53", "le pourcentage de la valeur booleenne est  "+key_1+" est exacte");
    // la premierse clé du tableau pourcentage
    var key_2= array[1];
    // le contenu de cette clé et on aarondit au centième près
    var value_key_2= pourcentage[key_1].toFixed(2);
    assert.equal(value_key_2, "39.53", "le pourcentage de la valeur booleenne  "+key_2+" est exacte");
  
});