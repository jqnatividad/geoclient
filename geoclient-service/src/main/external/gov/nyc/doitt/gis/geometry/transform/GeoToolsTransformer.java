package gov.nyc.doitt.gis.geometry.transform;

import gov.nyc.doitt.gis.geometry.domain.MapPoint;

import org.geotools.geometry.jts.CoordinateSequenceTransformer;
import org.geotools.geometry.jts.DefaultCoordinateSequenceTransformer;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

/**
 * Uses the GeoTools APIs to to coordinate transformations.
 * 
 * @author mlipper
 * 
 */
public class GeoToolsTransformer implements Transformer {

	private MathTransform math;
	private CoordinateReferenceSystem source;
	private CoordinateReferenceSystem target;
	/**
	 * Creates a new instance which will perform transformations from points
	 * given in the source CRS to the target CRS.
	 * 
	 * @param source
	 *            source coordinate reference system
	 * @param target
	 *            target coordinate reference system
	 * @throws CrsConfigurationException
	 *             if the underlying {@link CRS} class cannot find a math
	 *             transformation for the supplied CRSs
	 */
	public GeoToolsTransformer(CoordinateReferenceSystem source,
			CoordinateReferenceSystem target) throws CrsConfigurationException {
		super();
		this.source = source;
		this.target = target;
		try {
			this.math = CRS.findMathTransform(this.source, this.target);
		} catch (FactoryException e) {
			throw new CoordinateTranformationException(
					"Error configuring MathTransform.", e);
		}
	}

	/**
	 * Creates a point in the transformed target CRS.
	 * 
	 * @see gov.nyc.doitt.gis.geometry.transform.Transformer#transform(double,
	 * double)
	 */
	public MapPoint transform(double x, double y)
			throws CoordinateTranformationException {
		try {
			CoordinateSequence sequence = createCoordinateSequence(x, y);
			CoordinateSequenceTransformer transformer = createCoordinateSequenceTransformer();
			CoordinateSequence result = transformer.transform(sequence, math);
			return createPoint(result);
		} catch (TransformException te) {
			throw new CoordinateTranformationException(
					"Transformation of point x:" + x + ", y:" + y
							+ " failed during transformation.", te);
		}
	}

	MapPoint createPoint(CoordinateSequence sequence) {
		return new MapPoint(sequence.getX(0), sequence.getY(0));
	}

	CoordinateSequence createCoordinateSequence(double x, double y) {
		return new CoordinateArraySequence(new Coordinate[] { new Coordinate(x,
				y) });
	}

	CoordinateSequenceTransformer createCoordinateSequenceTransformer() {
		return new DefaultCoordinateSequenceTransformer();
	}

	/**
	 * For testing!
	 * @return the source CRS
	 */
	CoordinateReferenceSystem getSource() {
		return source;
	}

	/**
	 * For testing!
	 * @return the target CRS
	 */
	CoordinateReferenceSystem getTarget() {
		return target;
	}
	
	
}
