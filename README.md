# Software Fault Proneness Prediction Model

**Fault Proneness Prediction Models** are the trained models to predict important software quality attribute such as fault proneness using object oriented software metrics. Machine learning methods with optimization can be used for prediction of the software quality attributes. In this project, I find the answer to the question that are the fault proneness prediction models predicted using Neural Networks and Ant Colony Optimization method feasible and adaptable?


## Code Organization

Within the download you'll find the following directories and files. You'll see something like this:

```
src/main/java/de/chandanand/neuralnetwork/
├── model/
│   ├── Data.java
│   ├── NeuralNetwork.java
│   └── Neuron.java
├── util/
│   └── Utils.java
├── ACOFramework.java
└── ACOANNRunner.java
```

**resources** module contains training and test data for prediction model.


## Running the Project

```
$ git clone https://github.com/chandanand/software-fault-prediction.git
$ cd software-fault-prediction
$ mvn clean install
$ java -jar target/software-fault-prediction-1.0-SNAPSHOT.jar
```

## Results Achieved

### Analysis of parameter q
| q | Iterations for dataset |
| :---: | :---: |
| 0.06 | 59056 |
| 0.07 | 31789 |
| 0.08 | 23788 |
| 0.09 | 29682 |

### Analysis of parameter ξ
| ξ | Iterations for dataset |
| :---: | :---: |
| 0.65 | Did not coverage |
| 0.75 | 56789 |
| 0.80 | 29378 |
| 0.85 | 23788 |
| 0.9 | 36784 |

### Continuous ACO performance analysis
| Dataset | Training Cases | Backpropagation Iterations | Raw ACO Iterations | Normalized ACO Iterations | ACO efficiency |
| :---: | :---: | :---: | :---: | :---: | :---: |
| Ant | 210 | 33936163 | 23788 | 33303200 | 101% |

*Disclosure: Results may vary on every run and depending upon parameters*


## Creators

**Chand Anand**

* <https://twitter.com/chandanand>
* <https://github.com/chandanand>


## Copyright and license

Code released under [the MIT license](https://github.com/chandanand/ttp_with_gwo/blob/master/LICENSE).
