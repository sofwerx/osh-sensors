/***************************** BEGIN LICENSE BLOCK ***************************

 The contents of this file are copyright (C) 2010, Sensia Software LLC
 All Rights Reserved.

 Contributor(s): 
    Alexandre Robin <alex.robin@sensiasoftware.com>
 
******************************* END LICENSE BLOCK ***************************/

package org.sensorhub.api.persistence;

import org.sensorhub.api.module.IModuleManager;


/**
 * <p><b>Title:</b>
 * IPersistenceManager
 * </p>
 *
 * <p><b>Description:</b><br/>
 * Management interface for persistent storage modules
 * </p>
 *
 * <p>Copyright (c) 2010</p>
 * @author Alexandre Robin
 * @date Nov 5, 2010
 */
public interface IPersistenceManager extends IModuleManager<IDataStorage<?,?,?>>
{
	
    /**
	 * Destroys storage with specified local ID, with no recovery possibility
	 * @param storageId Local ID of storage to remove
	 * @param deleteAllData set to true to delete all data generated by this
	 * storage (this will remove all persistent data w/o possibility to recover)
	 */
	public void destroyStorage(String storageId, boolean deleteAllData) throws StorageException;
}