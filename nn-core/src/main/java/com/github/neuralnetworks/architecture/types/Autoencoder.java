package com.github.neuralnetworks.architecture.types;

import com.github.neuralnetworks.architecture.FullyConnected;
import com.github.neuralnetworks.architecture.Layer;
import com.github.neuralnetworks.architecture.NeuralNetworkImpl;
import com.github.neuralnetworks.calculation.neuronfunctions.ConstantConnectionCalculator;

/**
 * Autoencoder
 */
public class Autoencoder extends NeuralNetworkImpl {

    private Layer hiddenLayer;
    private Layer outputLayer;
    private boolean useHiddenLayerAsOutput = true;

    public Autoencoder(Layer inputLayer, Layer hiddenLayer, Layer outputLayer, boolean addBias) {
	this.hiddenLayer = hiddenLayer;
	this.outputLayer = outputLayer;

	// layers are added
	addLayer(inputLayer);
	addLayer(hiddenLayer);
	addLayer(outputLayer);

	// connections are created
	new FullyConnected(inputLayer, outputLayer);
	new FullyConnected(inputLayer, outputLayer);

	// biases are added
	if (addBias) {
	    Layer hiddenBiasLayer = new Layer(1, new ConstantConnectionCalculator(1));
	    addLayer(hiddenBiasLayer);
	    new FullyConnected(hiddenBiasLayer, hiddenLayer);

	    Layer outputBiasLayer = new Layer(1, new ConstantConnectionCalculator(1));
	    addLayer(outputBiasLayer);
	    new FullyConnected(outputBiasLayer, outputLayer);
	}
    }

    @Override
    public Layer getOutputLayer() {
	return useHiddenLayerAsOutput ? hiddenLayer : outputLayer;
    }

    @Override
    public Layer getDataOutputLayer() {
	return useHiddenLayerAsOutput ? hiddenLayer : outputLayer;
    }

    public boolean getUseHiddenLayerAsOutput() {
        return useHiddenLayerAsOutput;
    }

    public void setUseHiddenLayerAsOutput(boolean useHiddenLayerAsOutput) {
        this.useHiddenLayerAsOutput = useHiddenLayerAsOutput;
    }
}